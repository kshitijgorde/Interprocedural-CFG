import netscape.javascript.JSObject;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class protected extends public
{
    private String apa;
    private String bpa;
    private String cpa;
    private String dpa;
    private while e;
    private Vector epa;
    private Vector fpa;
    private Vector gpa;
    private String hpa;
    private String ipa;
    private String jpa;
    private double[] kpa;
    private double[] lpa;
    private double[] mpa;
    private double[] npa;
    private double[] opa;
    private double[] ppa;
    private double[] info;
    private static final String qpa = "try{";
    private static final String rpa = "}\n\tcatch(e){\n\t\tvar msg=\"\";\n\t\tif(__isIEBrowser() && typeof(e.description)!=\"undefined\")\n\t\t\tmsg=e.description;\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(e+\" \"+msg);\n\t\tthrow(msg);\n\t}\n";
    private static final String spa = "function __isIEBrowser(){\n\tvar agent=navigator.userAgent.toLowerCase();\n\treturn ((agent.indexOf(\"msie\") != -1) || (agent.indexOf(\"opera\") != -1))\n\t\t\t|| ((agent.indexOf(\"konqueror\") != -1) || (agent.indexOf(\"safari\") != -1));\n}\nfunction __checkParams(funcName, params, types, minParamValues){\n\tvar msg=\"\";\n\tvar error=false;\n\tif(types.length!=params.length){\n\t\terror=true;\n\t\tmsg+=\"Invalid number of arguments passed to function: '\" + funcName + \"'\";\n\t\tmsg+=\"\\nFunction '\" + funcName + \"' requires \" + types.length + \" arguments (\" + params.length + \" passed)\";\n\t}\n\tif(!error)\n\tfor(var i=0;i<types.length;i++){\n\t\tif((types[i] == \"array\" && (!(params[i] instanceof Object) || params[i].constructor.toString().indexOf(\"Array\") == -1))\n\t\t\t|| (types[i] != \"array\" && typeof(params[i]) != types[i])){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument types, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid type of argument \"+ (i+1) + \" '\" +typeof(params[i])+ \"', required type: \" + types[i];\n\t\t}\n\t}\n\tif(!error && typeof(minParamValues)!=\"undefined\")\n\tfor(var i=0;i<minParamValues.length;i++){\n\t\tif(typeof(params[i]) == \"number\" && params[i]<minParamValues[i]){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument value, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid value of argument \"+ (i+1) + \", value must be >= \" + minParamValues[i];\n\t\t}\n\t}\n\tif(error){\n\t\tthrow(\"Error: \"+msg);\n\t}\n}\nfunction Max(data,period){\n\t__checkParams(\"Max(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMax(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar max=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmax=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]>max) max=data[i-j];\n\t\tresult[i]=max;\n\t}\n\treturn result;\n}\nfunction Min(data,period){\n\t__checkParams(\"Min(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMin(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar min=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmin=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]<min) min=data[i-j];\n\t\tresult[i]=min;\n\t}\n\treturn result;\n}\nfunction ExpAvg(data,period){\n\t__checkParams(\"ExpAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateExpAvg(data,period);\n\tif(period<1) period=1;\n\tvar k = 2.0 / (period + 1);\n\tvar result=new Array(data.length);\n\tif(data.length>0) result[0]=data[0];\n\tfor(var i=1;i<data.length;i++)\n\t\tresult[i] = result[i - 1] * (1 - k) + data[i] * k;\n\treturn result;\n}\nfunction SimpleAvg(data,period){\n\t__checkParams(\"SimpleAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateSimpleAvg(data,period);\n\tif(period<1) period=1;\n\tvar sum=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length;i++){\n\t\tsum+=data[i];\n\t\tif(i-period>=0){\n\t\t\tsum-=data[i-period];\n\t\t\tresult[i]=sum/period;\n\t\t}\n\t\telse\n\t\t\tresult[i]=sum/(i+1);\n\t}\n\treturn result;\n}\nfunction StdDev(data,period){\n\t__checkParams(\"StdDev(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>200 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateStdDev(data,period);\n\tif(period<1) period=1;\n\tvar avg=SimpleAvg(data,period);\n\tvar dev=0.0;\n\tvar dif=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length && i<period;i++)\n\t\tresult[i]=0.0;\n\tfor(var i=period-1;i<data.length;i++){\n\t\tdev=0.0;\n\t\tfor(var j = i; j > i - period; j--){\n\t\t\tdif = data[j] - avg[i];\n\t\t\tdev += dif * dif;\n\t\t}\n\t\tresult[i] = Math.sqrt(dev / (period));\n\t}\n\treturn result;\n}\nfunction CreateArray(len){\n\t__checkParams(\"CreateArray(length)\",arguments,[\"number\"],[0]);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=0.0;\n\treturn tab;\n}\nfunction Param(val){\n\t__checkParams(\"Param(paramNumber)\",arguments,[\"number\"],[1]);\n\treturn document.FnChartsApplet.getParam(val);\n}\nfunction __GetValues(type){\n\tif(!__isIEBrowser()){\n\t\tvar tab=document.FnChartsApplet.getValues(type);\n\t\treturn tab;\n\t}\n\tvar len=document.FnChartsApplet.getValuesLength(type);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=document.FnChartsApplet.getValue(type,i);\n\treturn tab;\n}\nfunction Open(){\n\t__checkParams(\"Open()\",arguments,[]);\n\treturn __GetValues(1);\n}\nfunction High(){\n\t__checkParams(\"High()\",arguments,[]);\n\treturn __GetValues(2);\n}\nfunction Low(){\n\t__checkParams(\"Low()\",arguments,[]);\n\treturn __GetValues(3);\n}\nfunction Close(){\n\t__checkParams(\"Close()\",arguments,[]);\n\treturn __GetValues(4);\n}\nfunction Volume(){\n\t__checkParams(\"Volume()\",arguments,[]);\n\treturn __GetValues(5);\n}\nfunction OpenInt(){\n\t__checkParams(\"OpenInt()\",arguments,[]);\n\treturn __GetValues(6);\n}\nfunction Info(){\n\t__checkParams(\"Info()\",arguments,[]);\n\treturn __GetValues(7);\n}\nfunction AddGraph(graphData,firstValidIndex){\n\tif(typeof(firstValidIndex)==\"undefined\")\n\t\tfirstValidIndex=0;\n\t__checkParams(\"AddGraph(dataArray,firstValidIndex)\",[graphData,firstValidIndex],[\"array\",\"number\"],[,0]);\n\tvar closeLength=document.FnChartsApplet.getValuesLength(4);\n\tif(graphData.length!=closeLength)\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(\"Invalid length of dataArray passed to 'AddGraph()'\\nThe length must the same as the length of arrays returned by Close(),Open() etc. \");\n\tif(!__isIEBrowser()){\n\t\tdocument.FnChartsApplet.addGraphData(graphData,firstValidIndex);\n\t\treturn;\n\t}\n\tdocument.FnChartsApplet.beginGraphData(graphData.length);\n\tfor(var i=0;i<graphData.length;i++)\n\t\tdocument.FnChartsApplet.addGraphDataPoint(i,graphData[i]);\n\tdocument.FnChartsApplet.endGraphData(firstValidIndex);\n}\nfunction AddHorizLine(value){\n\t__checkParams(\"AddHorizLine(value)\",arguments,[\"number\"]);\n\tdocument.FnChartsApplet.addHorizLine(value);\n}\nfunction AddBuySignal(index){\n\t__checkParams(\"AddBuySignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addBuySignal(index);\n}\nfunction AddSellSignal(index){\n\t__checkParams(\"AddSellSignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addSellSignal(index);\n}\n";
    
    public protected(final String apa, final String s, final int[] array, final String bpa, final while e) {
        super(s, 0, array, null, e._());
        this.epa = new Vector();
        this.fpa = new Vector();
        this.gpa = new Vector();
        this.hpa = null;
        this.ipa = null;
        this.jpa = null;
        this.e = e;
        this.apa = apa;
        this.bpa = bpa;
        super.Uoa = new int[3];
        this.G();
    }
    
    public gea b() {
        String string = "";
        if (super.Oa != null) {
            for (int i = 0; i < super.Oa.length; ++i) {
                string = string + super.Oa[i] + ",";
            }
        }
        return new gea(this.apa.substring(1), super.name, string, this.bpa);
    }
    
    private static double[] a(final double[] array) {
        if (array == null) {
            return new double[0];
        }
        final double[] array2 = new double[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    protected void H() {
        this.epa.removeAllElements();
        this.fpa.removeAllElements();
        this.gpa.removeAllElements();
        super.Woa = null;
        super.Yoa = null;
        super.Xoa = 0;
        for (int i = 0; i < super.Uoa.length; ++i) {
            super.Uoa[i] = 0;
        }
        super.Zoa = null;
        this.cpa = null;
        this.dpa = null;
        this.e._(this);
        try {
            this.kpa = a(super.Voa.b());
            this.lpa = a(super.Voa._());
            this.mpa = a(super.Voa.g());
            this.npa = a(super.Voa.h());
            this.opa = a(super.Voa.i());
            this.ppa = a(super.Voa.j());
            this.info = a(super.Voa.f());
            final JSObject jsObject = (JSObject)this.e.b();
            if (this.hpa == null || this.ipa == null || this.jpa == null) {
                final String c = this.e.C();
                if (c != null && c.length() > 0) {
                    this.hpa = a.b("try{", "FnChartsApplet", c);
                    this.ipa = a.b("}\n\tcatch(e){\n\t\tvar msg=\"\";\n\t\tif(__isIEBrowser() && typeof(e.description)!=\"undefined\")\n\t\t\tmsg=e.description;\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(e+\" \"+msg);\n\t\tthrow(msg);\n\t}\n", "FnChartsApplet", c);
                    this.jpa = a.b("function __isIEBrowser(){\n\tvar agent=navigator.userAgent.toLowerCase();\n\treturn ((agent.indexOf(\"msie\") != -1) || (agent.indexOf(\"opera\") != -1))\n\t\t\t|| ((agent.indexOf(\"konqueror\") != -1) || (agent.indexOf(\"safari\") != -1));\n}\nfunction __checkParams(funcName, params, types, minParamValues){\n\tvar msg=\"\";\n\tvar error=false;\n\tif(types.length!=params.length){\n\t\terror=true;\n\t\tmsg+=\"Invalid number of arguments passed to function: '\" + funcName + \"'\";\n\t\tmsg+=\"\\nFunction '\" + funcName + \"' requires \" + types.length + \" arguments (\" + params.length + \" passed)\";\n\t}\n\tif(!error)\n\tfor(var i=0;i<types.length;i++){\n\t\tif((types[i] == \"array\" && (!(params[i] instanceof Object) || params[i].constructor.toString().indexOf(\"Array\") == -1))\n\t\t\t|| (types[i] != \"array\" && typeof(params[i]) != types[i])){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument types, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid type of argument \"+ (i+1) + \" '\" +typeof(params[i])+ \"', required type: \" + types[i];\n\t\t}\n\t}\n\tif(!error && typeof(minParamValues)!=\"undefined\")\n\tfor(var i=0;i<minParamValues.length;i++){\n\t\tif(typeof(params[i]) == \"number\" && params[i]<minParamValues[i]){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument value, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid value of argument \"+ (i+1) + \", value must be >= \" + minParamValues[i];\n\t\t}\n\t}\n\tif(error){\n\t\tthrow(\"Error: \"+msg);\n\t}\n}\nfunction Max(data,period){\n\t__checkParams(\"Max(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMax(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar max=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmax=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]>max) max=data[i-j];\n\t\tresult[i]=max;\n\t}\n\treturn result;\n}\nfunction Min(data,period){\n\t__checkParams(\"Min(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMin(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar min=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmin=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]<min) min=data[i-j];\n\t\tresult[i]=min;\n\t}\n\treturn result;\n}\nfunction ExpAvg(data,period){\n\t__checkParams(\"ExpAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateExpAvg(data,period);\n\tif(period<1) period=1;\n\tvar k = 2.0 / (period + 1);\n\tvar result=new Array(data.length);\n\tif(data.length>0) result[0]=data[0];\n\tfor(var i=1;i<data.length;i++)\n\t\tresult[i] = result[i - 1] * (1 - k) + data[i] * k;\n\treturn result;\n}\nfunction SimpleAvg(data,period){\n\t__checkParams(\"SimpleAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateSimpleAvg(data,period);\n\tif(period<1) period=1;\n\tvar sum=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length;i++){\n\t\tsum+=data[i];\n\t\tif(i-period>=0){\n\t\t\tsum-=data[i-period];\n\t\t\tresult[i]=sum/period;\n\t\t}\n\t\telse\n\t\t\tresult[i]=sum/(i+1);\n\t}\n\treturn result;\n}\nfunction StdDev(data,period){\n\t__checkParams(\"StdDev(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>200 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateStdDev(data,period);\n\tif(period<1) period=1;\n\tvar avg=SimpleAvg(data,period);\n\tvar dev=0.0;\n\tvar dif=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length && i<period;i++)\n\t\tresult[i]=0.0;\n\tfor(var i=period-1;i<data.length;i++){\n\t\tdev=0.0;\n\t\tfor(var j = i; j > i - period; j--){\n\t\t\tdif = data[j] - avg[i];\n\t\t\tdev += dif * dif;\n\t\t}\n\t\tresult[i] = Math.sqrt(dev / (period));\n\t}\n\treturn result;\n}\nfunction CreateArray(len){\n\t__checkParams(\"CreateArray(length)\",arguments,[\"number\"],[0]);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=0.0;\n\treturn tab;\n}\nfunction Param(val){\n\t__checkParams(\"Param(paramNumber)\",arguments,[\"number\"],[1]);\n\treturn document.FnChartsApplet.getParam(val);\n}\nfunction __GetValues(type){\n\tif(!__isIEBrowser()){\n\t\tvar tab=document.FnChartsApplet.getValues(type);\n\t\treturn tab;\n\t}\n\tvar len=document.FnChartsApplet.getValuesLength(type);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=document.FnChartsApplet.getValue(type,i);\n\treturn tab;\n}\nfunction Open(){\n\t__checkParams(\"Open()\",arguments,[]);\n\treturn __GetValues(1);\n}\nfunction High(){\n\t__checkParams(\"High()\",arguments,[]);\n\treturn __GetValues(2);\n}\nfunction Low(){\n\t__checkParams(\"Low()\",arguments,[]);\n\treturn __GetValues(3);\n}\nfunction Close(){\n\t__checkParams(\"Close()\",arguments,[]);\n\treturn __GetValues(4);\n}\nfunction Volume(){\n\t__checkParams(\"Volume()\",arguments,[]);\n\treturn __GetValues(5);\n}\nfunction OpenInt(){\n\t__checkParams(\"OpenInt()\",arguments,[]);\n\treturn __GetValues(6);\n}\nfunction Info(){\n\t__checkParams(\"Info()\",arguments,[]);\n\treturn __GetValues(7);\n}\nfunction AddGraph(graphData,firstValidIndex){\n\tif(typeof(firstValidIndex)==\"undefined\")\n\t\tfirstValidIndex=0;\n\t__checkParams(\"AddGraph(dataArray,firstValidIndex)\",[graphData,firstValidIndex],[\"array\",\"number\"],[,0]);\n\tvar closeLength=document.FnChartsApplet.getValuesLength(4);\n\tif(graphData.length!=closeLength)\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(\"Invalid length of dataArray passed to 'AddGraph()'\\nThe length must the same as the length of arrays returned by Close(),Open() etc. \");\n\tif(!__isIEBrowser()){\n\t\tdocument.FnChartsApplet.addGraphData(graphData,firstValidIndex);\n\t\treturn;\n\t}\n\tdocument.FnChartsApplet.beginGraphData(graphData.length);\n\tfor(var i=0;i<graphData.length;i++)\n\t\tdocument.FnChartsApplet.addGraphDataPoint(i,graphData[i]);\n\tdocument.FnChartsApplet.endGraphData(firstValidIndex);\n}\nfunction AddHorizLine(value){\n\t__checkParams(\"AddHorizLine(value)\",arguments,[\"number\"]);\n\tdocument.FnChartsApplet.addHorizLine(value);\n}\nfunction AddBuySignal(index){\n\t__checkParams(\"AddBuySignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addBuySignal(index);\n}\nfunction AddSellSignal(index){\n\t__checkParams(\"AddSellSignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addSellSignal(index);\n}\n", "FnChartsApplet", c);
                }
                else {
                    this.hpa = "try{";
                    this.ipa = "}\n\tcatch(e){\n\t\tvar msg=\"\";\n\t\tif(__isIEBrowser() && typeof(e.description)!=\"undefined\")\n\t\t\tmsg=e.description;\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(e+\" \"+msg);\n\t\tthrow(msg);\n\t}\n";
                    this.jpa = "function __isIEBrowser(){\n\tvar agent=navigator.userAgent.toLowerCase();\n\treturn ((agent.indexOf(\"msie\") != -1) || (agent.indexOf(\"opera\") != -1))\n\t\t\t|| ((agent.indexOf(\"konqueror\") != -1) || (agent.indexOf(\"safari\") != -1));\n}\nfunction __checkParams(funcName, params, types, minParamValues){\n\tvar msg=\"\";\n\tvar error=false;\n\tif(types.length!=params.length){\n\t\terror=true;\n\t\tmsg+=\"Invalid number of arguments passed to function: '\" + funcName + \"'\";\n\t\tmsg+=\"\\nFunction '\" + funcName + \"' requires \" + types.length + \" arguments (\" + params.length + \" passed)\";\n\t}\n\tif(!error)\n\tfor(var i=0;i<types.length;i++){\n\t\tif((types[i] == \"array\" && (!(params[i] instanceof Object) || params[i].constructor.toString().indexOf(\"Array\") == -1))\n\t\t\t|| (types[i] != \"array\" && typeof(params[i]) != types[i])){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument types, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid type of argument \"+ (i+1) + \" '\" +typeof(params[i])+ \"', required type: \" + types[i];\n\t\t}\n\t}\n\tif(!error && typeof(minParamValues)!=\"undefined\")\n\tfor(var i=0;i<minParamValues.length;i++){\n\t\tif(typeof(params[i]) == \"number\" && params[i]<minParamValues[i]){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument value, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid value of argument \"+ (i+1) + \", value must be >= \" + minParamValues[i];\n\t\t}\n\t}\n\tif(error){\n\t\tthrow(\"Error: \"+msg);\n\t}\n}\nfunction Max(data,period){\n\t__checkParams(\"Max(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMax(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar max=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmax=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]>max) max=data[i-j];\n\t\tresult[i]=max;\n\t}\n\treturn result;\n}\nfunction Min(data,period){\n\t__checkParams(\"Min(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMin(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar min=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmin=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]<min) min=data[i-j];\n\t\tresult[i]=min;\n\t}\n\treturn result;\n}\nfunction ExpAvg(data,period){\n\t__checkParams(\"ExpAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateExpAvg(data,period);\n\tif(period<1) period=1;\n\tvar k = 2.0 / (period + 1);\n\tvar result=new Array(data.length);\n\tif(data.length>0) result[0]=data[0];\n\tfor(var i=1;i<data.length;i++)\n\t\tresult[i] = result[i - 1] * (1 - k) + data[i] * k;\n\treturn result;\n}\nfunction SimpleAvg(data,period){\n\t__checkParams(\"SimpleAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateSimpleAvg(data,period);\n\tif(period<1) period=1;\n\tvar sum=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length;i++){\n\t\tsum+=data[i];\n\t\tif(i-period>=0){\n\t\t\tsum-=data[i-period];\n\t\t\tresult[i]=sum/period;\n\t\t}\n\t\telse\n\t\t\tresult[i]=sum/(i+1);\n\t}\n\treturn result;\n}\nfunction StdDev(data,period){\n\t__checkParams(\"StdDev(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>200 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateStdDev(data,period);\n\tif(period<1) period=1;\n\tvar avg=SimpleAvg(data,period);\n\tvar dev=0.0;\n\tvar dif=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length && i<period;i++)\n\t\tresult[i]=0.0;\n\tfor(var i=period-1;i<data.length;i++){\n\t\tdev=0.0;\n\t\tfor(var j = i; j > i - period; j--){\n\t\t\tdif = data[j] - avg[i];\n\t\t\tdev += dif * dif;\n\t\t}\n\t\tresult[i] = Math.sqrt(dev / (period));\n\t}\n\treturn result;\n}\nfunction CreateArray(len){\n\t__checkParams(\"CreateArray(length)\",arguments,[\"number\"],[0]);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=0.0;\n\treturn tab;\n}\nfunction Param(val){\n\t__checkParams(\"Param(paramNumber)\",arguments,[\"number\"],[1]);\n\treturn document.FnChartsApplet.getParam(val);\n}\nfunction __GetValues(type){\n\tif(!__isIEBrowser()){\n\t\tvar tab=document.FnChartsApplet.getValues(type);\n\t\treturn tab;\n\t}\n\tvar len=document.FnChartsApplet.getValuesLength(type);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=document.FnChartsApplet.getValue(type,i);\n\treturn tab;\n}\nfunction Open(){\n\t__checkParams(\"Open()\",arguments,[]);\n\treturn __GetValues(1);\n}\nfunction High(){\n\t__checkParams(\"High()\",arguments,[]);\n\treturn __GetValues(2);\n}\nfunction Low(){\n\t__checkParams(\"Low()\",arguments,[]);\n\treturn __GetValues(3);\n}\nfunction Close(){\n\t__checkParams(\"Close()\",arguments,[]);\n\treturn __GetValues(4);\n}\nfunction Volume(){\n\t__checkParams(\"Volume()\",arguments,[]);\n\treturn __GetValues(5);\n}\nfunction OpenInt(){\n\t__checkParams(\"OpenInt()\",arguments,[]);\n\treturn __GetValues(6);\n}\nfunction Info(){\n\t__checkParams(\"Info()\",arguments,[]);\n\treturn __GetValues(7);\n}\nfunction AddGraph(graphData,firstValidIndex){\n\tif(typeof(firstValidIndex)==\"undefined\")\n\t\tfirstValidIndex=0;\n\t__checkParams(\"AddGraph(dataArray,firstValidIndex)\",[graphData,firstValidIndex],[\"array\",\"number\"],[,0]);\n\tvar closeLength=document.FnChartsApplet.getValuesLength(4);\n\tif(graphData.length!=closeLength)\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(\"Invalid length of dataArray passed to 'AddGraph()'\\nThe length must the same as the length of arrays returned by Close(),Open() etc. \");\n\tif(!__isIEBrowser()){\n\t\tdocument.FnChartsApplet.addGraphData(graphData,firstValidIndex);\n\t\treturn;\n\t}\n\tdocument.FnChartsApplet.beginGraphData(graphData.length);\n\tfor(var i=0;i<graphData.length;i++)\n\t\tdocument.FnChartsApplet.addGraphDataPoint(i,graphData[i]);\n\tdocument.FnChartsApplet.endGraphData(firstValidIndex);\n}\nfunction AddHorizLine(value){\n\t__checkParams(\"AddHorizLine(value)\",arguments,[\"number\"]);\n\tdocument.FnChartsApplet.addHorizLine(value);\n}\nfunction AddBuySignal(index){\n\t__checkParams(\"AddBuySignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addBuySignal(index);\n}\nfunction AddSellSignal(index){\n\t__checkParams(\"AddSellSignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addSellSignal(index);\n}\n";
                }
            }
            String s = this.hpa + this.bpa + this.ipa + "\n" + this.jpa;
            if (this.e._().c() && !this.e._().d()) {
                s = s.replace('\r', ' ').replace('\n', ' ');
            }
            jsObject.eval(s);
            super.Xoa = this.epa.size();
            if (super.Xoa == 0) {
                super.Woa = null;
            }
            else {
                super.Woa = new double[super.Xoa][this.npa.length];
                for (int j = 0; j < this.epa.size(); ++j) {
                    final double[] array = this.epa.elementAt(j);
                    for (int n = 0; n < array.length && n < this.npa.length; ++n) {
                        super.Woa[j][n] = array[n];
                    }
                }
                this.epa.removeAllElements();
                for (int n2 = 0; n2 < super.Woa.length && n2 < super.Uoa.length; ++n2) {
                    for (int n3 = super.Uoa[n2], n4 = 0; n4 < n3 && n3 < super.Woa[0].length; ++n4) {
                        super.Woa[n2][n4] = super.Woa[n2][n3];
                    }
                }
                super.Yoa = new byte[this.npa.length];
                for (int k = 0; k < this.fpa.size(); ++k) {
                    final int intValue = this.fpa.elementAt(k);
                    if (intValue >= 0 && intValue < super.Yoa.length) {
                        super.Yoa[intValue] = 1;
                    }
                }
                this.fpa.removeAllElements();
                for (int l = 0; l < this.gpa.size(); ++l) {
                    final int intValue2 = this.gpa.elementAt(l);
                    if (intValue2 >= 0 && intValue2 < super.Yoa.length) {
                        super.Yoa[intValue2] = -1;
                    }
                }
                this.gpa.removeAllElements();
            }
            if (this.dpa != null) {
                this.cpa = this.dpa;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if (this.dpa != null) {
                this.cpa = this.dpa + "\n" + ex.getMessage();
            }
            else {
                this.cpa = ex.getMessage();
            }
        }
        final double[] kpa = null;
        this.info = kpa;
        this.ppa = kpa;
        this.opa = kpa;
        this.npa = kpa;
        this.mpa = kpa;
        this.lpa = kpa;
        this.kpa = kpa;
    }
    
    public void a(final double[] array, int n) {
        if (array == null || this.npa == null || array.length != this.npa.length) {
            return;
        }
        if (this.epa.size() >= 3) {
            return;
        }
        if (n < 0 || n >= this.npa.length) {
            n = 0;
        }
        if (this.epa.size() < super.Uoa.length) {
            super.Uoa[this.epa.size()] = n;
        }
        this.epa.addElement(array);
    }
    
    protected void G() {
    }
    
    public int b(final int n) {
        if (n >= 0 && n < super.Uoa.length) {
            return super.Uoa[n];
        }
        return 0;
    }
    
    public String toString() {
        return this.apa;
    }
    
    public String c() {
        if (this.cpa != null) {
            return this.cpa;
        }
        return super.name;
    }
    
    public int I() {
        if (super._pa) {
            this.H();
        }
        super._pa = false;
        return super.I();
    }
    
    public int J() {
        if (super._pa) {
            this.H();
        }
        super._pa = false;
        if (super.Zoa == null) {
            return 0;
        }
        return super.Zoa.length;
    }
    
    public double b(final int n) {
        if (super._pa) {
            this.H();
        }
        super._pa = false;
        if (super.Zoa == null) {
            return 0.0;
        }
        return super.Zoa[n];
    }
    
    public void b(final double n) {
        double[] zoa;
        if (super.Zoa == null) {
            zoa = new double[] { 0.0 };
        }
        else {
            zoa = new double[super.Zoa.length + 1];
            for (int i = 0; i < super.Zoa.length; ++i) {
                zoa[i] = super.Zoa[i];
            }
        }
        zoa[zoa.length - 1] = n;
        super.Zoa = zoa;
    }
    
    public void addBuySignal(final int n) {
        if (n >= 0 && n < this.npa.length) {
            this.fpa.addElement(new Integer(n));
        }
    }
    
    public void addSellSignal(final int n) {
        if (n >= 0 && n < this.npa.length) {
            this.gpa.addElement(new Integer(n));
        }
    }
    
    public double[] Q() {
        return this.npa;
    }
    
    public double[] R() {
        return this.lpa;
    }
    
    public double[] S() {
        return this.mpa;
    }
    
    public double[] T() {
        return this.kpa;
    }
    
    public double[] U() {
        return this.ppa;
    }
    
    public double[] V() {
        return this.opa;
    }
    
    public double[] W() {
        return this.info;
    }
    
    public void s(final String dpa) {
        if (this.dpa != null) {
            this.dpa = this.dpa + "\n" + dpa;
        }
        else {
            this.dpa = dpa;
        }
    }
}
