import netscape.javascript.JSObject;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class n extends o
{
    private String xIb;
    private String yIb;
    private String zIb;
    private String AIb;
    private g iGb;
    private Vector BIb;
    private Vector CIb;
    private Vector DIb;
    private String EIb;
    private String FIb;
    private String GIb;
    private double[] bHb;
    private double[] cHb;
    private double[] dHb;
    private double[] eHb;
    private double[] fHb;
    private double[] gHb;
    private static final String HIb = "try{";
    private static final String IIb = "}\n\tcatch(e){\n\t\tvar msg=\"\";\n\t\tif(__isIEBrowser() && typeof(e.description)!=\"undefined\")\n\t\t\tmsg=e.description;\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(e+\" \"+msg);\n\t\tthrow(msg);\n\t}\n";
    private static final String JIb = "function __isIEBrowser(){\n\tvar agent=navigator.userAgent.toLowerCase();\n\treturn ((agent.indexOf(\"msie\") != -1) || (agent.indexOf(\"opera\") != -1))\n\t\t\t|| ((agent.indexOf(\"konqueror\") != -1) || (agent.indexOf(\"safari\") != -1));\n}\nfunction __checkParams(funcName, params, types, minParamValues){\n\tvar msg=\"\";\n\tvar error=false;\n\tif(types.length!=params.length){\n\t\terror=true;\n\t\tmsg+=\"Invalid number of arguments passed to function: '\" + funcName + \"'\";\n\t\tmsg+=\"\\nFunction '\" + funcName + \"' requires \" + types.length + \" arguments (\" + params.length + \" passed)\";\n\t}\n\tif(!error)\n\tfor(var i=0;i<types.length;i++){\n\t\tif((types[i] == \"array\" && !(params[i] instanceof Array))\n\t\t\t|| (types[i] != \"array\" && typeof(params[i]) != types[i])){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument types, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid type of argument \"+ (i+1) + \" '\" +typeof(params[i])+ \"', required type: \" + types[i];\n\t\t}\n\t}\n\tif(!error && typeof(minParamValues)!=\"undefined\")\n\tfor(var i=0;i<minParamValues.length;i++){\n\t\tif(typeof(params[i]) == \"number\" && params[i]<minParamValues[i]){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument value, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid value of argument \"+ (i+1) + \", value must be >= \" + minParamValues[i];\n\t\t}\n\t}\n\tif(error){\n\t\tthrow(\"Error: \"+msg);\n\t}\n}\nfunction Max(data,period){\n\t__checkParams(\"Max(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMax(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar max=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmax=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]>max) max=data[i-j];\n\t\tresult[i]=max;\n\t}\n\treturn result;\n}\nfunction Min(data,period){\n\t__checkParams(\"Min(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMin(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar min=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmin=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]<min) min=data[i-j];\n\t\tresult[i]=min;\n\t}\n\treturn result;\n}\nfunction ExpAvg(data,period){\n\t__checkParams(\"ExpAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateExpAvg(data,period);\n\tif(period<1) period=1;\n\tvar k = 2.0 / (period + 1);\n\tvar result=new Array(data.length);\n\tif(data.length>0) result[0]=data[0];\n\tfor(var i=1;i<data.length;i++)\n\t\tresult[i] = result[i - 1] * (1 - k) + data[i] * k;\n\treturn result;\n}\nfunction SimpleAvg(data,period){\n\t__checkParams(\"SimpleAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateSimpleAvg(data,period);\n\tif(period<1) period=1;\n\tvar sum=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length;i++){\n\t\tsum+=data[i];\n\t\tif(i-period>=0){\n\t\t\tsum-=data[i-period];\n\t\t\tresult[i]=sum/period;\n\t\t}\n\t\telse\n\t\t\tresult[i]=sum/(i+1);\n\t}\n\treturn result;\n}\nfunction StdDev(data,period){\n\t__checkParams(\"StdDev(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>200 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateStdDev(data,period);\n\tif(period<1) period=1;\n\tvar avg=SimpleAvg(data,period);\n\tvar dev=0.0;\n\tvar dif=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length && i<period;i++)\n\t\tresult[i]=0.0;\n\tfor(var i=period-1;i<data.length;i++){\n\t\tdev=0.0;\n\t\tfor(var j = i; j > i - period; j--){\n\t\t\tdif = data[j] - avg[i];\n\t\t\tdev += dif * dif;\n\t\t}\n\t\tresult[i] = Math.sqrt(dev / (period));\n\t}\n\treturn result;\n}\nfunction CreateArray(len){\n\t__checkParams(\"CreateArray(length)\",arguments,[\"number\"],[0]);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=0.0;\n\treturn tab;\n}\nfunction Param(val){\n\t__checkParams(\"Param(paramNumber)\",arguments,[\"number\"],[1]);\n\treturn document.FnChartsApplet.getParam(val);\n}\nfunction __GetValues(type){\n\tif(!__isIEBrowser()){\n\t\tvar tab=document.FnChartsApplet.getValues(type);\n\t\treturn tab;\n\t}\n\tvar len=document.FnChartsApplet.getValuesLength(type);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=document.FnChartsApplet.getValue(type,i);\n\treturn tab;\n}\nfunction Open(){\n\t__checkParams(\"Open()\",arguments,[]);\n\treturn __GetValues(1);\n}\nfunction High(){\n\t__checkParams(\"High()\",arguments,[]);\n\treturn __GetValues(2);\n}\nfunction Low(){\n\t__checkParams(\"Low()\",arguments,[]);\n\treturn __GetValues(3);\n}\nfunction Close(){\n\t__checkParams(\"Close()\",arguments,[]);\n\treturn __GetValues(4);\n}\nfunction Volume(){\n\t__checkParams(\"Volume()\",arguments,[]);\n\treturn __GetValues(5);\n}\nfunction OpenInt(){\n\t__checkParams(\"OpenInt()\",arguments,[]);\n\treturn __GetValues(6);\n}\nfunction AddGraph(graphData,firstValidIndex){\n\tif(typeof(firstValidIndex)==\"undefined\")\n\t\tfirstValidIndex=0;\n\t__checkParams(\"AddGraph(dataArray,firstValidIndex)\",[graphData,firstValidIndex],[\"array\",\"number\"],[,0]);\n\tvar closeLength=document.FnChartsApplet.getValuesLength(4);\n\tif(graphData.length!=closeLength)\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(\"Invalid length of dataArray passed to 'AddGraph()'\\nThe length must the same as the length of arrays returned by Close(),Open() etc. \");\n\tif(!__isIEBrowser()){\n\t\tdocument.FnChartsApplet.addGraphData(graphData,firstValidIndex);\n\t\treturn;\n\t}\n\tdocument.FnChartsApplet.beginGraphData(graphData.length);\n\tfor(var i=0;i<graphData.length;i++)\n\t\tdocument.FnChartsApplet.addGraphDataPoint(i,graphData[i]);\n\tdocument.FnChartsApplet.endGraphData(firstValidIndex);\n}\nfunction AddHorizLine(value){\n\t__checkParams(\"AddHorizLine(value)\",arguments,[\"number\"]);\n\tdocument.FnChartsApplet.addHorizLine(value);\n}\nfunction AddBuySignal(index){\n\t__checkParams(\"AddBuySignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addBuySignal(index);\n}\nfunction AddSellSignal(index){\n\t__checkParams(\"AddSellSignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addSellSignal(index);\n}\n";
    
    public n(final String xIb, final String s, final int[] array, final String yIb, final g iGb) {
        super(s, 0, array, null, iGb._());
        this.BIb = new Vector();
        this.CIb = new Vector();
        this.DIb = new Vector();
        this.EIb = null;
        this.FIb = null;
        this.GIb = null;
        this.iGb = iGb;
        this.xIb = xIb;
        this.yIb = yIb;
        super.qIb = new int[3];
        this.C();
    }
    
    public To b() {
        String string = "";
        if (super.DDb != null) {
            for (int i = 0; i < super.DDb.length; ++i) {
                string = string + super.DDb[i] + ",";
            }
        }
        return new To(this.xIb.substring(1), super.name, string, this.yIb);
    }
    
    private static double[] a(final double[] array) {
        if (array == null) {
            return new double[0];
        }
        final double[] array2 = new double[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    protected void D() {
        this.BIb.removeAllElements();
        this.CIb.removeAllElements();
        this.DIb.removeAllElements();
        super.sIb = null;
        super.uIb = null;
        super.tIb = 0;
        for (int i = 0; i < super.qIb.length; ++i) {
            super.qIb[i] = 0;
        }
        super.vIb = null;
        this.zIb = null;
        this.AIb = null;
        this.iGb.b(this);
        try {
            this.bHb = a(super.rIb.i());
            this.cHb = a(super.rIb.j());
            this.dHb = a(super.rIb.k());
            this.eHb = a(super.rIb.l());
            this.fHb = a(super.rIb.m());
            this.gHb = a(super.rIb.n());
            final JSObject jsObject = (JSObject)this.iGb._();
            if (this.EIb == null || this.FIb == null || this.GIb == null) {
                final String a = this.iGb.A();
                if (a != null && a.length() > 0) {
                    this.EIb = i.b("try{", "FnChartsApplet", a);
                    this.FIb = i.b("}\n\tcatch(e){\n\t\tvar msg=\"\";\n\t\tif(__isIEBrowser() && typeof(e.description)!=\"undefined\")\n\t\t\tmsg=e.description;\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(e+\" \"+msg);\n\t\tthrow(msg);\n\t}\n", "FnChartsApplet", a);
                    this.GIb = i.b("function __isIEBrowser(){\n\tvar agent=navigator.userAgent.toLowerCase();\n\treturn ((agent.indexOf(\"msie\") != -1) || (agent.indexOf(\"opera\") != -1))\n\t\t\t|| ((agent.indexOf(\"konqueror\") != -1) || (agent.indexOf(\"safari\") != -1));\n}\nfunction __checkParams(funcName, params, types, minParamValues){\n\tvar msg=\"\";\n\tvar error=false;\n\tif(types.length!=params.length){\n\t\terror=true;\n\t\tmsg+=\"Invalid number of arguments passed to function: '\" + funcName + \"'\";\n\t\tmsg+=\"\\nFunction '\" + funcName + \"' requires \" + types.length + \" arguments (\" + params.length + \" passed)\";\n\t}\n\tif(!error)\n\tfor(var i=0;i<types.length;i++){\n\t\tif((types[i] == \"array\" && !(params[i] instanceof Array))\n\t\t\t|| (types[i] != \"array\" && typeof(params[i]) != types[i])){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument types, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid type of argument \"+ (i+1) + \" '\" +typeof(params[i])+ \"', required type: \" + types[i];\n\t\t}\n\t}\n\tif(!error && typeof(minParamValues)!=\"undefined\")\n\tfor(var i=0;i<minParamValues.length;i++){\n\t\tif(typeof(params[i]) == \"number\" && params[i]<minParamValues[i]){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument value, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid value of argument \"+ (i+1) + \", value must be >= \" + minParamValues[i];\n\t\t}\n\t}\n\tif(error){\n\t\tthrow(\"Error: \"+msg);\n\t}\n}\nfunction Max(data,period){\n\t__checkParams(\"Max(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMax(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar max=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmax=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]>max) max=data[i-j];\n\t\tresult[i]=max;\n\t}\n\treturn result;\n}\nfunction Min(data,period){\n\t__checkParams(\"Min(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMin(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar min=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmin=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]<min) min=data[i-j];\n\t\tresult[i]=min;\n\t}\n\treturn result;\n}\nfunction ExpAvg(data,period){\n\t__checkParams(\"ExpAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateExpAvg(data,period);\n\tif(period<1) period=1;\n\tvar k = 2.0 / (period + 1);\n\tvar result=new Array(data.length);\n\tif(data.length>0) result[0]=data[0];\n\tfor(var i=1;i<data.length;i++)\n\t\tresult[i] = result[i - 1] * (1 - k) + data[i] * k;\n\treturn result;\n}\nfunction SimpleAvg(data,period){\n\t__checkParams(\"SimpleAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateSimpleAvg(data,period);\n\tif(period<1) period=1;\n\tvar sum=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length;i++){\n\t\tsum+=data[i];\n\t\tif(i-period>=0){\n\t\t\tsum-=data[i-period];\n\t\t\tresult[i]=sum/period;\n\t\t}\n\t\telse\n\t\t\tresult[i]=sum/(i+1);\n\t}\n\treturn result;\n}\nfunction StdDev(data,period){\n\t__checkParams(\"StdDev(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>200 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateStdDev(data,period);\n\tif(period<1) period=1;\n\tvar avg=SimpleAvg(data,period);\n\tvar dev=0.0;\n\tvar dif=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length && i<period;i++)\n\t\tresult[i]=0.0;\n\tfor(var i=period-1;i<data.length;i++){\n\t\tdev=0.0;\n\t\tfor(var j = i; j > i - period; j--){\n\t\t\tdif = data[j] - avg[i];\n\t\t\tdev += dif * dif;\n\t\t}\n\t\tresult[i] = Math.sqrt(dev / (period));\n\t}\n\treturn result;\n}\nfunction CreateArray(len){\n\t__checkParams(\"CreateArray(length)\",arguments,[\"number\"],[0]);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=0.0;\n\treturn tab;\n}\nfunction Param(val){\n\t__checkParams(\"Param(paramNumber)\",arguments,[\"number\"],[1]);\n\treturn document.FnChartsApplet.getParam(val);\n}\nfunction __GetValues(type){\n\tif(!__isIEBrowser()){\n\t\tvar tab=document.FnChartsApplet.getValues(type);\n\t\treturn tab;\n\t}\n\tvar len=document.FnChartsApplet.getValuesLength(type);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=document.FnChartsApplet.getValue(type,i);\n\treturn tab;\n}\nfunction Open(){\n\t__checkParams(\"Open()\",arguments,[]);\n\treturn __GetValues(1);\n}\nfunction High(){\n\t__checkParams(\"High()\",arguments,[]);\n\treturn __GetValues(2);\n}\nfunction Low(){\n\t__checkParams(\"Low()\",arguments,[]);\n\treturn __GetValues(3);\n}\nfunction Close(){\n\t__checkParams(\"Close()\",arguments,[]);\n\treturn __GetValues(4);\n}\nfunction Volume(){\n\t__checkParams(\"Volume()\",arguments,[]);\n\treturn __GetValues(5);\n}\nfunction OpenInt(){\n\t__checkParams(\"OpenInt()\",arguments,[]);\n\treturn __GetValues(6);\n}\nfunction AddGraph(graphData,firstValidIndex){\n\tif(typeof(firstValidIndex)==\"undefined\")\n\t\tfirstValidIndex=0;\n\t__checkParams(\"AddGraph(dataArray,firstValidIndex)\",[graphData,firstValidIndex],[\"array\",\"number\"],[,0]);\n\tvar closeLength=document.FnChartsApplet.getValuesLength(4);\n\tif(graphData.length!=closeLength)\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(\"Invalid length of dataArray passed to 'AddGraph()'\\nThe length must the same as the length of arrays returned by Close(),Open() etc. \");\n\tif(!__isIEBrowser()){\n\t\tdocument.FnChartsApplet.addGraphData(graphData,firstValidIndex);\n\t\treturn;\n\t}\n\tdocument.FnChartsApplet.beginGraphData(graphData.length);\n\tfor(var i=0;i<graphData.length;i++)\n\t\tdocument.FnChartsApplet.addGraphDataPoint(i,graphData[i]);\n\tdocument.FnChartsApplet.endGraphData(firstValidIndex);\n}\nfunction AddHorizLine(value){\n\t__checkParams(\"AddHorizLine(value)\",arguments,[\"number\"]);\n\tdocument.FnChartsApplet.addHorizLine(value);\n}\nfunction AddBuySignal(index){\n\t__checkParams(\"AddBuySignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addBuySignal(index);\n}\nfunction AddSellSignal(index){\n\t__checkParams(\"AddSellSignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addSellSignal(index);\n}\n", "FnChartsApplet", a);
                }
                else {
                    this.EIb = "try{";
                    this.FIb = "}\n\tcatch(e){\n\t\tvar msg=\"\";\n\t\tif(__isIEBrowser() && typeof(e.description)!=\"undefined\")\n\t\t\tmsg=e.description;\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(e+\" \"+msg);\n\t\tthrow(msg);\n\t}\n";
                    this.GIb = "function __isIEBrowser(){\n\tvar agent=navigator.userAgent.toLowerCase();\n\treturn ((agent.indexOf(\"msie\") != -1) || (agent.indexOf(\"opera\") != -1))\n\t\t\t|| ((agent.indexOf(\"konqueror\") != -1) || (agent.indexOf(\"safari\") != -1));\n}\nfunction __checkParams(funcName, params, types, minParamValues){\n\tvar msg=\"\";\n\tvar error=false;\n\tif(types.length!=params.length){\n\t\terror=true;\n\t\tmsg+=\"Invalid number of arguments passed to function: '\" + funcName + \"'\";\n\t\tmsg+=\"\\nFunction '\" + funcName + \"' requires \" + types.length + \" arguments (\" + params.length + \" passed)\";\n\t}\n\tif(!error)\n\tfor(var i=0;i<types.length;i++){\n\t\tif((types[i] == \"array\" && !(params[i] instanceof Array))\n\t\t\t|| (types[i] != \"array\" && typeof(params[i]) != types[i])){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument types, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid type of argument \"+ (i+1) + \" '\" +typeof(params[i])+ \"', required type: \" + types[i];\n\t\t}\n\t}\n\tif(!error && typeof(minParamValues)!=\"undefined\")\n\tfor(var i=0;i<minParamValues.length;i++){\n\t\tif(typeof(params[i]) == \"number\" && params[i]<minParamValues[i]){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument value, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid value of argument \"+ (i+1) + \", value must be >= \" + minParamValues[i];\n\t\t}\n\t}\n\tif(error){\n\t\tthrow(\"Error: \"+msg);\n\t}\n}\nfunction Max(data,period){\n\t__checkParams(\"Max(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMax(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar max=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmax=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]>max) max=data[i-j];\n\t\tresult[i]=max;\n\t}\n\treturn result;\n}\nfunction Min(data,period){\n\t__checkParams(\"Min(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMin(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar min=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmin=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]<min) min=data[i-j];\n\t\tresult[i]=min;\n\t}\n\treturn result;\n}\nfunction ExpAvg(data,period){\n\t__checkParams(\"ExpAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateExpAvg(data,period);\n\tif(period<1) period=1;\n\tvar k = 2.0 / (period + 1);\n\tvar result=new Array(data.length);\n\tif(data.length>0) result[0]=data[0];\n\tfor(var i=1;i<data.length;i++)\n\t\tresult[i] = result[i - 1] * (1 - k) + data[i] * k;\n\treturn result;\n}\nfunction SimpleAvg(data,period){\n\t__checkParams(\"SimpleAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateSimpleAvg(data,period);\n\tif(period<1) period=1;\n\tvar sum=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length;i++){\n\t\tsum+=data[i];\n\t\tif(i-period>=0){\n\t\t\tsum-=data[i-period];\n\t\t\tresult[i]=sum/period;\n\t\t}\n\t\telse\n\t\t\tresult[i]=sum/(i+1);\n\t}\n\treturn result;\n}\nfunction StdDev(data,period){\n\t__checkParams(\"StdDev(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>200 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateStdDev(data,period);\n\tif(period<1) period=1;\n\tvar avg=SimpleAvg(data,period);\n\tvar dev=0.0;\n\tvar dif=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length && i<period;i++)\n\t\tresult[i]=0.0;\n\tfor(var i=period-1;i<data.length;i++){\n\t\tdev=0.0;\n\t\tfor(var j = i; j > i - period; j--){\n\t\t\tdif = data[j] - avg[i];\n\t\t\tdev += dif * dif;\n\t\t}\n\t\tresult[i] = Math.sqrt(dev / (period));\n\t}\n\treturn result;\n}\nfunction CreateArray(len){\n\t__checkParams(\"CreateArray(length)\",arguments,[\"number\"],[0]);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=0.0;\n\treturn tab;\n}\nfunction Param(val){\n\t__checkParams(\"Param(paramNumber)\",arguments,[\"number\"],[1]);\n\treturn document.FnChartsApplet.getParam(val);\n}\nfunction __GetValues(type){\n\tif(!__isIEBrowser()){\n\t\tvar tab=document.FnChartsApplet.getValues(type);\n\t\treturn tab;\n\t}\n\tvar len=document.FnChartsApplet.getValuesLength(type);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=document.FnChartsApplet.getValue(type,i);\n\treturn tab;\n}\nfunction Open(){\n\t__checkParams(\"Open()\",arguments,[]);\n\treturn __GetValues(1);\n}\nfunction High(){\n\t__checkParams(\"High()\",arguments,[]);\n\treturn __GetValues(2);\n}\nfunction Low(){\n\t__checkParams(\"Low()\",arguments,[]);\n\treturn __GetValues(3);\n}\nfunction Close(){\n\t__checkParams(\"Close()\",arguments,[]);\n\treturn __GetValues(4);\n}\nfunction Volume(){\n\t__checkParams(\"Volume()\",arguments,[]);\n\treturn __GetValues(5);\n}\nfunction OpenInt(){\n\t__checkParams(\"OpenInt()\",arguments,[]);\n\treturn __GetValues(6);\n}\nfunction AddGraph(graphData,firstValidIndex){\n\tif(typeof(firstValidIndex)==\"undefined\")\n\t\tfirstValidIndex=0;\n\t__checkParams(\"AddGraph(dataArray,firstValidIndex)\",[graphData,firstValidIndex],[\"array\",\"number\"],[,0]);\n\tvar closeLength=document.FnChartsApplet.getValuesLength(4);\n\tif(graphData.length!=closeLength)\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(\"Invalid length of dataArray passed to 'AddGraph()'\\nThe length must the same as the length of arrays returned by Close(),Open() etc. \");\n\tif(!__isIEBrowser()){\n\t\tdocument.FnChartsApplet.addGraphData(graphData,firstValidIndex);\n\t\treturn;\n\t}\n\tdocument.FnChartsApplet.beginGraphData(graphData.length);\n\tfor(var i=0;i<graphData.length;i++)\n\t\tdocument.FnChartsApplet.addGraphDataPoint(i,graphData[i]);\n\tdocument.FnChartsApplet.endGraphData(firstValidIndex);\n}\nfunction AddHorizLine(value){\n\t__checkParams(\"AddHorizLine(value)\",arguments,[\"number\"]);\n\tdocument.FnChartsApplet.addHorizLine(value);\n}\nfunction AddBuySignal(index){\n\t__checkParams(\"AddBuySignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addBuySignal(index);\n}\nfunction AddSellSignal(index){\n\t__checkParams(\"AddSellSignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addSellSignal(index);\n}\n";
                }
            }
            String s = this.EIb + this.yIb + this.FIb + "\n" + this.GIb;
            if (this.iGb.b().Y() && !this.iGb.b().Z()) {
                s = s.replace('\r', ' ').replace('\n', ' ');
            }
            jsObject.eval(s);
            super.tIb = this.BIb.size();
            if (super.tIb == 0) {
                super.sIb = null;
            }
            else {
                super.sIb = new double[super.tIb][this.eHb.length];
                for (int j = 0; j < this.BIb.size(); ++j) {
                    final double[] array = this.BIb.elementAt(j);
                    for (int n = 0; n < array.length && n < this.eHb.length; ++n) {
                        super.sIb[j][n] = array[n];
                    }
                }
                this.BIb.removeAllElements();
                for (int n2 = 0; n2 < super.sIb.length && n2 < super.qIb.length; ++n2) {
                    for (int n3 = super.qIb[n2], n4 = 0; n4 < n3 && n3 < super.sIb[0].length; ++n4) {
                        super.sIb[n2][n4] = super.sIb[n2][n3];
                    }
                }
                super.uIb = new byte[this.eHb.length];
                for (int k = 0; k < this.CIb.size(); ++k) {
                    final int intValue = this.CIb.elementAt(k);
                    if (intValue >= 0 && intValue < super.uIb.length) {
                        super.uIb[intValue] = 1;
                    }
                }
                this.CIb.removeAllElements();
                for (int l = 0; l < this.DIb.size(); ++l) {
                    final int intValue2 = this.DIb.elementAt(l);
                    if (intValue2 >= 0 && intValue2 < super.uIb.length) {
                        super.uIb[intValue2] = -1;
                    }
                }
                this.DIb.removeAllElements();
            }
            if (this.AIb != null) {
                this.zIb = this.AIb;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if (this.AIb != null) {
                this.zIb = this.AIb + "\n" + ex.getMessage();
            }
            else {
                this.zIb = ex.getMessage();
            }
        }
        final double[] array2 = null;
        this.gHb = array2;
        this.fHb = array2;
        this.eHb = array2;
        this.dHb = array2;
        this.cHb = array2;
        this.bHb = array2;
    }
    
    public void b(final double[] array, int n) {
        if (array == null || this.eHb == null || array.length != this.eHb.length) {
            return;
        }
        if (this.BIb.size() >= 3) {
            return;
        }
        if (n < 0 || n >= this.eHb.length) {
            n = 0;
        }
        if (this.BIb.size() < super.qIb.length) {
            super.qIb[this.BIb.size()] = n;
        }
        this.BIb.addElement(array);
    }
    
    protected void C() {
    }
    
    public int _(final int n) {
        if (n >= 0 && n < super.qIb.length) {
            return super.qIb[n];
        }
        return 0;
    }
    
    public String toString() {
        return this.xIb;
    }
    
    public String l() {
        if (this.zIb != null) {
            return this.zIb;
        }
        return super.name;
    }
    
    public int J() {
        if (super.wIb) {
            this.D();
        }
        super.wIb = false;
        return super.J();
    }
    
    public int K() {
        if (super.wIb) {
            this.D();
        }
        super.wIb = false;
        if (super.vIb == null) {
            return 0;
        }
        return super.vIb.length;
    }
    
    public double f(final int n) {
        if (super.wIb) {
            this.D();
        }
        super.wIb = false;
        if (super.vIb == null) {
            return 0.0;
        }
        return super.vIb[n];
    }
    
    public void a(final double n) {
        double[] vIb;
        if (super.vIb == null) {
            vIb = new double[] { 0.0 };
        }
        else {
            vIb = new double[super.vIb.length + 1];
            for (int i = 0; i < super.vIb.length; ++i) {
                vIb[i] = super.vIb[i];
            }
        }
        vIb[vIb.length - 1] = n;
        super.vIb = vIb;
    }
    
    public void addBuySignal(final int n) {
        if (n >= 0 && n < this.eHb.length) {
            this.CIb.addElement(new Integer(n));
        }
    }
    
    public void addSellSignal(final int n) {
        if (n >= 0 && n < this.eHb.length) {
            this.DIb.addElement(new Integer(n));
        }
    }
    
    public double[] q() {
        return this.eHb;
    }
    
    public double[] r() {
        return this.cHb;
    }
    
    public double[] s() {
        return this.dHb;
    }
    
    public double[] t() {
        return this.bHb;
    }
    
    public double[] u() {
        return this.gHb;
    }
    
    public double[] v() {
        return this.fHb;
    }
    
    public void C(final String aIb) {
        if (this.AIb != null) {
            this.AIb = this.AIb + "\n" + aIb;
        }
        else {
            this.AIb = aIb;
        }
    }
}
