import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class o
{
    private String[][] Rsa;
    private Hashtable Ssa;
    
    public o() {
        this.Rsa = new String[][] { { "cbNoIndicator", "------" }, { "cbDaily", "Daily" }, { "cbWeekly", "Weekly" }, { "cbMonthly", "Monthly" }, { "cbIntraday", "Intraday" }, { "cbIntraday1s", "1 sec" }, { "cbIntraday2s", "2 sec" }, { "cbIntraday3s", "3 sec" }, { "cbIntraday4s", "4 sec" }, { "cbIntraday5s", "5 sec" }, { "cbIntraday10s", "10 sec" }, { "cbIntraday15s", "15 sec" }, { "cbIntraday20s", "20 sec" }, { "cbIntraday30s", "30 sec" }, { "cbIntraday1", "1 min" }, { "cbIntraday2", "2 min" }, { "cbIntraday3", "3 min" }, { "cbIntraday4", "4 min" }, { "cbIntraday5", "5 min" }, { "cbIntraday10", "10 min" }, { "cbIntraday15", "15 min" }, { "cbIntraday20", "20 min" }, { "cbIntraday30", "30 min" }, { "cbIntraday60", "60 min" }, { "cbRange_1month", "1m" }, { "cbRange_3months", "3m" }, { "cbRange_6months", "6m" }, { "cbRange_1year", "1y" }, { "cbRange_2years", "2y" }, { "cbRange_3years", "3y" }, { "cbRange_5years", "5y" }, { "cbRange_10years", "10y" }, { "cbRange_all", "All" }, { "cbRange_free", "" }, { "strEnterSymbolHere", "Enter symbol" }, { "strDate", "" }, { "strPriceTitle", "Price" }, { "strVolume", "Volume" }, { "strOpenInterest", "Open Interest" }, { "strTimeframeInfo", "These settings allow to change the amount of data that will be downloaded and processed\nby the program. Only the most recent data (data inside selected timeframe) will be available\nfor display. The shorter the timeframe value, the faster data processing is.\nThis option may be especially useful when using low end computer and/or slow Internet\nconnection." }, { "strIntradayTimeframe", "Intraday timeframe (number of trading sessions)" }, { "strTimeframe", "Historical timeframe (number of years)" }, { "strTimeframeSettings", "Timeframe settings" }, { "cbTimeframe0", "all available data" }, { "btnOK", "OK" }, { "btnCancel", "Cancel" }, { "strChartProperties", "Chart customization" }, { "strAdditionalChartProperties", "Chart customization" }, { "btnAdditionalSettings", "More settings..." }, { "strExtraPrice0", "Extra price line #1" }, { "strExtraPrice1", "Extra price line #2" }, { "strExtraPrice2", "Extra price line #3" }, { "strExtraPrice3", "Extra price line #4" }, { "strExtraPrice4", "Extra price line #5" }, { "strExtraPrice5", "Extra price line #6" }, { "strExtraPrice6", "Extra price line #7" }, { "strExtraPrice7", "Extra price line #8" }, { "strExtraPrice8", "Extra price line #9" }, { "strExtraPrice9", "Extra price line #10" }, { "strChart", "Chart element" }, { "strColor", "Color" }, { "strLineWidth", "Line width" }, { "strPrice", "Price (and Doji candle)" }, { "strBullishCandle", "Bullish candle" }, { "strBearishCandle", "Bearish candle" }, { "strBgPrice", "Secondary price" }, { "strVOLEMA", "Volume average (VOL.EMA)" }, { "strBOL", "Bollinger Bands (BOL)" }, { "strParabolicSAR", "Parabolic SAR (Par.SAR)" }, { "strPivotPoints", "Pivot Points (PIV PTS)" }, { "strDMA", "Displaced Moving Average (DMA)" }, { "strEMA1", "Exponential average #1 (EMA1)" }, { "strEMA2", "Exponential average #2 (EMA2)" }, { "strEMA3", "Exponential average #3 (EMA3)" }, { "strSMA1", "Simple moving average #1 (SMA1)" }, { "strSMA2", "Simple moving average #2 (SMA2)" }, { "strSMA3", "Simple moving average #3 (SMA3)" }, { "strTrendLine", "Trendlines, support/resistance" }, { "strCrosshair", "Crosshair" }, { "strIndicatorLine0", "Indicator line #1 (main)" }, { "strIndicatorLine1", "Indicator line #2 (Signal)" }, { "strIndicatorLine2", "Indicator line #3" }, { "strSelectColor", "Select color" }, { "cbWidth0", "1 - fastest drawing" }, { "cbWidth1", "2" }, { "cbWidth2", "3" }, { "cbWidth3", "4" }, { "cbWidth4", "5" }, { "cbWidth5", "6 - greatest width" }, { "strIndicatorSettings", "Settings" }, { "btnOK-EnableIndicator", "Apply settings" }, { "btnCancel-DisableIndicator", "Cancel and switch off" }, { "strCalculation", "Calculation period:" }, { "rbMonthly", "Month" }, { "rbWeekly", "Week" }, { "rbDaily", "Day" }, { "rbArbitraryPeriod", "Arbitrary:" }, { "strDisplaySettings", "Elements to display:" }, { "cbDisplayPivotPointLevels", "Pivot Point levels" }, { "cbDisplayR1S1Levels", "Primary support and resistance levels (S1, R1)" }, { "cbDisplayR2S2Levels", "Secondary support and resistance levels (S2, R2)" }, { "rbDisplayAllPeriods", "Display all calculated levels" }, { "rbDisplayLastPeriodOnly", "Display the most recent levels only" }, { "strIndicatorSettingsError", "Invalid indicator settings" }, { "msgNoDisplayElementSelected", "No elements to display. At least one element must be selected." }, { "msgInvalidPeriodCount", "Invalid calculation period. The period must be between 1 and 999." }, { "btnColorAndWidthSettings", "Color and width settings..." }, { "strPivotPointsAppearance", "Pivot Points appearance settings" }, { "strR1", "Primary resistance level (R1)" }, { "strS1", "Primary support level (S1)" }, { "strR2", "Secondary resistance level (R2)" }, { "strS2", "Secondary support level (S2)" }, { "strAvgType", "Avg. type:" }, { "rbSimpleAvg", "arithmetic" }, { "rbExpAvg", "exponential" }, { "strAvgPeriod", "Period:" }, { "strDisplacement", "Displacement:" }, { "rbDisplaceBackward", "left" }, { "rbDisplaceForward", "right" }, { "msgInvalidAvgPeriod", "Invalid period value. The value must be between 2 and 999." }, { "msgInvalidDisplacement", "Invalid displacement value. The value must be between 0 and 999." }, { "btnBuy", "Buy" }, { "btnSell", "Sell" }, { "msgNoDataAvailable", "No data available" }, { "msgLoadingData", "Loading data" }, { "msgRefreshingData", "Refreshing data..." }, { "msgInvalidData", "Invalid data" }, { "msgEnterSymbol", "Enter symbol to view the chart" }, { "msgDataLoaded", "Data loaded" }, { "msgFnChartsMaximized", "FnCharts applet is currently running in another window." }, { "Caption", "Interactive Financial Charts" }, { "msgLoadingConfiguration", "Loading preferences..." }, { "msgConfigurationLoaded", "Preferences loaded." }, { "msgConfigurationLoadError", "Cannot load preferences." }, { "msgSavingConfiguration", "Saving preferences..." }, { "msgConfigurationSaved", "Preferences saved." }, { "msgConfigurationSaveError", "Cannot save preferences." }, { "msgLoadingStudies", "Loading studies..." }, { "msgStudiesLoaded", "Studies loaded." }, { "msgStudiesLoadError", "Cannot load studies." }, { "msgSavingStudies", "Saving studies..." }, { "msgStudiesSaved", "Studies saved." }, { "msgStudiesSaveError", "Cannot save studies." }, { "msgCopyingChartToClipboard", "Copying chart to clipboard..." }, { "msgChartCopiedToClipboard", "Chart copied to clipboard." }, { "msgErrorCopyingChartToClipboard", "Couldn't copy chart to clipboard." }, { "msgPrintingChart", "Printing the chart..." }, { "msgPrintError", "Couldn't print the chart." }, { "msgGeneratingChartImage", "Generating chart image..." }, { "msgChartImageGenerated", "Chart image generated." }, { "msgErrorGeneratingChartImage", "Couldn't generate chart image." }, { "menuGridVisible", "Show grid" }, { "menuTitleVisible", "Show title(s)" }, { "menuLegendVisible", "Show legend" }, { "menuAnnotationsVisible", "Show annotations" }, { "menuCrosshairVisible", "Show crosshair" }, { "menuBuySellSignalsVisible", "Show Buy/Sell signals" }, { "menuTrendLine", "Trendlines" }, { "menuSupportResistance", "Support/resistance levels" }, { "menuFibonacciRetracements", "Fibonacci retracements" }, { "menuRegularRetracements", "Regular retracements" }, { "menuTextTool", "Text" }, { "menuArrowUp", "Up arrow" }, { "menuArrowDown", "Down arrow" }, { "menuToolModeAuto", "Automatic drawing" }, { "menuToolModeDraw", "Draw new objects" }, { "menuToolModeMove", "Move existing objects" }, { "menuToolModeDelete", "Delete existing objects" }, { "menuDeleteAllTools", "Remove all objects" }, { "menuSetAxisMargins", "Set margins for axes" }, { "strFavorites", "Favorites" }, { "menuClearSymbolSelection", "Clear selection" }, { "menuAddToFavorites", "Add to favorites" }, { "menuRemoveFromFavorites", "Remove from favorites" }, { "menuClearFavorites", "Remove all favorites" }, { "strChartFg", "Background" }, { "strGrid", "Grid" }, { "msgStartingProgram", "Starting program..." }, { "msgIndicatorCalculationBegin", "Calculating indicators..." }, { "msgIndicatorCalculationEnd", "Calculations finished" }, { "menuDefineIndicator", "Define new indicator..." }, { "menuEditIndicator", "Edit indicator..." }, { "menuDeleteIndicator", "Delete indicator" }, { "strJSIndicatorDefinition", "Indicator definition" }, { "strJSIndicatorName", "Name:" }, { "strJSIndicatorDescription", "Descr.:" }, { "strJSIndicatorParams", "Parameters:" }, { "strEnterIndicatorCodeHere", "Enter indicator code below" }, { "strJSIndicatorDefinitionError", "Indicator definition error" }, { "strJSIndicatorNameFieldEmpty", "Indicator name must not be empty." }, { "strJSIndicatorNameFieldInvalid", "Invalid indicator name.\nIndicator name can only contain letters, digits, or the following characters % &" }, { "strJSIndicatorDescriptionFieldInvalid", "Invalid indicator description.\nIndicator description can only contain\n  letters, digits, spaces or the following characters:\n  - _ % : . ( ) &" }, { "strJSIndicatorCodeFieldEmpty", "Undefined indicator algorithm.\nYou must enter a JavaScript algorithm.\nExamples are available at the bottom of the indicator definition window." }, { "strJSIndicatorCodeFieldInvalid", "Indicator algorithm must not contain any the following:\n  <![CDATA[\n  ]]>\n  //\nFor comments, use /* comment */ construct" }, { "strJSIndicatorParamFieldInvalid", "Invalid indicator parameter.\nParameter value must be a number between 2 and 999." }, { "strConfirmIndicatorRemovalTitle", "Remove indicator" }, { "strConfirmIndicatorRemovalMessage", "Confirm indicator removal" }, { "strConfirmIndicatorOverwriteTitle", "Overwrite indicator" }, { "strConfirmIndicatorOverwriteMessage", "Indicator already exists. Confirm overwrite" }, { "btnYes", "Yes" }, { "btnNo", "No" }, { "strEditTextTitle", "Edit text" }, { "strEditTextMessage", "Edit text on the chart:" }, { "strInputTextTitle", "Text input" }, { "strInputTextMessage", "Enter text to place on the chart:" }, { "strInputTextEmptyTitle", "Empty text" }, { "strInputTextEmptyMessage", "You must enter a text." }, { "strSetAxisMarginsTitle", "Set margins" }, { "strInputAxisMargins", "Input margin values" }, { "strPriceAxisMaxMargin", "Price axis top margin" }, { "strPriceAxisMinMargin", "Price axis bottom margin" }, { "strTimeAxisMaxMargin", "Time axis right margin" }, { "strTimeAxisMinMargin", "Time axis left margin" }, { "strAxisMarginFormat", "pixels" }, { "btnLogCor_Info", "Logarithmic scale with axis correlation" }, { "btnLogUncor_Info", "Logarithmic scale without axis correlation" }, { "btnLinCor_Info", "Linear scale with axis correlation" }, { "btnLinUncor_Info", "Linear scale without axis correlation" }, { "btnPercentCor_Info", "Percent scale (enables performance comparison for multiple symbols)" }, { "btnStockListSwitch_Info", "Show/hide symbol entry form" }, { "btnVolumeSwitch_Info", "Show/hide volume panel" }, { "btnSetTimeframe_Info", "Timeframe settings..." }, { "btnChartProperties_Info", "Chart customization..." }, { "btnUseBrowserCache_Info", "Enable/disable browser cache usage" }, { "btnAutoRefreshSwitch_Info", "Enable/disable automatic data refresh" }, { "btnAutoRefreshBeep_Info", "Enable/disable data refresh beep" }, { "selectQuotType_Info", "Data compression" }, { "selectCategory_Info", "Select category" }, { "btnLine_Info", "Line chart" }, { "btnOhlc_Info", "Bar chart (OHLC)" }, { "btnCandle_Info", "Candlestick chart" }, { "btnSaveConfig_Info", "Save preferences" }, { "btnLoadConfig_Info", "Load preferences" }, { "btnSaveStudies_Info", "Save studies" }, { "btnLoadStudies_Info", "Load studies" }, { "btnAutoLoadStudies_Info", "Enable/disable automatic loading of studies" }, { "btnCopyChart_Info", "Copy chart to clipboard" }, { "btnPrintChart_Info", "Print the chart" }, { "btnMaximizeChart_Info", "Enable/disable chart area maximization" }, { "enterPrimarySymbol_Info", "Enter symbol and press [ENTER] to download data" }, { "enterSecondarySymbol_Info", "Enter secondary symbol and press [ENTER] to download data" }, { "selectStock_Info", "Select stock. Right-click to display pop-up menu." }, { "selectStockMulti_Info", "Select stock. Right-click to display pop-up menu." }, { "btnMaximize_Info", "Switch on/off program window maximization" }, { "btnRefresh_Info", "Refresh chart data" }, { "btnZoomOut_Info", "Zoom out" }, { "btnZoomIn_Info", "Zoom in" }, { "btnMoveChart_Info", "Scroll the chart" }, { "selectRange_Info", "Select preferred range (actual range depends on the amount of data available)" }, { "btnBuy_Info", "Buy" }, { "btnSell_Info", "Sell" }, { "txtIndicatorParam_Info", "Enter indicator parameter here" }, { "btnIndicatorOnOff_Info", "Switch indicator on/off" }, { "selectIndicator_Info", "Select indicator" }, { "strPopupMenu_Info", "Pop-up menu available. Right-click to display pop-up menu." }, { "selectIndicatorDefinitionEnabled_Info", "Select indicator. Right-click to define indicator." } };
        this.Ssa = null;
        final String[][] rsa = this.Rsa;
        this.Ssa = new Hashtable(rsa.length);
        for (int i = 0; i < rsa.length; ++i) {
            this.Ssa.put(rsa[i][0], rsa[i][1]);
        }
        this.Ssa.put("strJSIndicatorCodeHelp", "-------------------------------------------------------------------------\n                CREATING YOUR OWN INDICATORS IN FnCharts\n-------------------------------------------------------------------------\n\nThis document contains the following information:\n- sample indicator definitions\n- the short description of indicator definition rules\n- the list of predefined functions that can be used in indicator\n  definitions\n- performance related tips\n- error messages related information\n\n-------------------------------------------------------------------------\n                       SAMPLE INDICATOR DEFINITIONS\n-------------------------------------------------------------------------\n\n/* User-defined ROC (Rate of Change)\n * - short version\n * You can copy the following algorithm into the indicator definition\n * window.\n * Remember to enter the default value of the first parameter (i.e. 5)\n * into the parameter field.\n */\n\nvar close = Close();\nvar roc = CreateArray(close.length);\nvar n = Param(1);\nfor(var i=n; i<close.length; i++)\n\troc[i] = 100.0 * (close[i] - close[i-n]) / close[i-n];\nAddGraph(roc,n);\nAddHorizLine(0);\n\n/* End of user-defined ROC */\n\n-------------------------------------------------------------------------\n\n/* User-defined ROC (Rate of Change)\n * - long version with comments\n * You can copy the following algorithm into the indicator definition\n * window.\n * Remember to enter the default value of the first parameter (i.e. 5)\n * into the parameter field.\n */\n\n/* Obtain the array of closing price values using predefined Close()\n * function and store these values in the 'close' variable.\n */\n\nvar close = Close();\n\n/* Create the array to store calculated ROC values for every trading\n * session. To perform this operation, use predefined CreateArray(length)\n * function.\n * Caution:\n * The length of created 'roc' array must be the same as the length of\n * afore-mentioned 'close' array.\n * Predefined CreateArray() function will initially set values of each\n * array element to zero.\n */\n\nvar roc = CreateArray(close.length);\n\n/* Obtain the value of the first indicator parameter and store that value\n * in the variable called 'n'\n * Caution:\n * The default value of the first parameter (i.e. 5) must be entered into\n * the first parameter field.\n */\n\nvar n = Param(1);\n\n/* For each trading session (numbered with 'i') calculate ROC value\n * and store that value in corresponding element of the 'roc' array.\n * Caution:\n * Trading sessions are numbered from 0 up to length-1.\n * The oldest trading session is numbered as 0, and the newest trading\n * session (most recent one) is numbered as length-1.\n */\n\nfor(var i=n; i<close.length; i++)\n\troc[i] = 100.0 * (close[i] - close[i-n]) / close[i-n];\n\n/* Take the array of ROC values, and add graph for that array using\n * predefined function AddGraph(dataArray, firstValidIndex)\n * Caution:\n * 'firstValidIndex' is the first index of the array element that has\n * its value properly calculated.\n */\n\nAddGraph(roc,n);\n\n/* Add graph for horizontal axis (horizontal line at 0 coordinate) */\n\nAddHorizLine(0);\n\n/* End of user-defined ROC */\n\n-------------------------------------------------------------------------\n\n/* User-defined MACD (Moving Average Convergence Divergence)\n * - short version\n * You can copy the following algorithm into the indicator definition\n * window.\n * Remember to fill-in all three parameter fields with default parameter\n * values (i.e. 12, 26, 9)\n */\n\nvar close = Close();\nvar avg1 = ExpAvg(close,Param(1));\nvar avg2 = ExpAvg(close,Param(2));\nvar macd = CreateArray(avg1.length);\nfor(var i=0; i<avg1.length; i++)\n\tmacd[i] = avg1[i] - avg2[i];\nAddGraph(macd,Param(2));\nvar signal = ExpAvg(macd,Param(3));\nAddGraph(signal,Param(2)+Param(3));\nAddHorizLine(0);\n\n/* The following instructions add buy/sell signals to the MACD chart\n * window.\n */\n\nvar begin=Param(2)+Param(3);\nfor(var i=begin; i<macd.length; i++){\n        if((macd[i-1] < signal[i-1]) && (macd[i]>signal[i]))\n                AddBuySignal(i);\n        else\n        if((macd[i-1] > signal[i-1]) && (macd[i]<signal[i]))\n                AddSellSignal(i);\n}\n\n/* End of user-defined MACD */\n\n-------------------------------------------------------------------------\n\n/* User-defined MACD (Moving Average Convergence Divergence)\n * - long version with comments\n * You can copy the following algorithm into the indicator definition\n * window.\n * Remember to fill-in all three parameter fields with default parameter\n * values (i.e. 12, 26, 9)\n */\n\n/* Define three parameter variables and store actual parameter values\n * in these variables.\n */\n\nvar param1 = Param(1);\nvar param2 = Param(2);\nvar param3 = Param(3);\n\n/* Obtain the array of closing price values using predefined Close()\n * function and store these values in the 'close' variable.\n */\n\nvar close = Close();\n\n/* Calculate first exponential average of closing price values, using\n * predefined ExpAvg(dataArray,period) function and store the result\n * in avg1 variable.\n */\n\nvar avg1 = ExpAvg(close,param1);\n\n/* Calculate second exponential average of closing price values, using\n * predefined ExpAvg(dataArray,period) function and store the result\n * in avg2 variable.\n */\n\nvar avg2 = ExpAvg(close,param2);\n\n/* Create the array to store calculated MACD values for every trading\n * session. To perform this operation, use predefined CreateArray(length)\n * function.\n */\n\nvar macd = CreateArray(avg1.length);\n\n/* For each trading session, calculate MACD value and store that value in\n * 'macd' array.\n */\n\nfor(var i=0; i<avg1.length; i++)\n\tmacd[i] = avg1[i] - avg2[i];\n\n/* Take the array of MACD values, and add graph for that array using\n * predefined function AddGraph(dataArray, firstValidIndex)\n * Note the value of the first valid index.\n */\n\nAddGraph(macd,param2);\n\n/* Calculate values for the Signal line and strore them in 'signal' array.\n * (the Signal line is an exponential average of MACD values.\n */\n\nvar signal = ExpAvg(macd,param3);\n\n/* Take the array of Signal values, and add graph for that array using\n * predefined function AddGraph(dataArray, firstValidIndex)\n * Note the value of the first valid index.\n */\n\nAddGraph(signal,param2+param3);\n\n/* Add the horizontal zero line to MACD chart.\n */\n\nAddHorizLine(0);\n\n/* Find buy/sell signal occurrences.\n * Begin search from the array element, where both MACD and Signal values\n * are properly calculated.\n */\n\nvar begin=param2+param3;\n\n/* Find buy/sell signal occurrences and add them to the MACD chart.\n * Buy signal occurs when the MACD line crosses the Signal line and moves\n * upwards.\n * Buy signal occurs when the MACD line crosses the Signal line and moves\n * upwards.\n */\n\nfor(var i=begin; i<macd.length; i++){\n        if((macd[i-1] < signal[i-1]) && (macd[i]>signal[i]))\n                AddBuySignal(i);\n        else\n        if((macd[i-1] > signal[i-1]) && (macd[i]<signal[i]))\n                AddSellSignal(i);\n}\n\n/* End of user-defined MACD */\n\n-------------------------------------------------------------------------\n\n/* User-defined %R (Williams' Percent Range)\n * - short version\n * You can copy the following algorithm into the indicator definition\n * window.\n * Remember to fill-in all three parameter fields with default parameter\n * values (i.e. 10, 20, 80)\n */\n\nvar n = Param(1);\nvar max = Max(High(),n);\nvar min = Min(Low(),n);\nvar close = Close();\nvar percentR = CreateArray(close.length);\nfor(var i=0; i<close.length; i++)\n\tif(max[i]-min[i] != 0)\n\t\tpercentR[i] = 100.0 * (close[i]-min[i]) / (max[i]-min[i]);\n\telse\n\t\tpercentR[i] = 100.0;\n\nAddGraph(percentR,n);\nAddHorizLine(Param(2));\nAddHorizLine(Param(3));\n\n/* End of user-defined %R */\n\n-------------------------------------------------------------------------\n\n/* User-defined %R (Williams' Percent Range)\n * - long version with comments\n * You can copy the following algorithm into the indicator definition\n * window.\n * Remember to fill-in all three parameter fields with default parameter\n * values (i.e. 10, 20, 80)\n */\n\n/* Obtain the value of the first parameter (period) and store it in\n * the variable called 'n'.\n * Caution:\n * The default value of the first parameter (i.e. 10) must be entered\n * into the first parameter field.\n */\n\nvar n = Param(1);\n\n/* Calculate maximum values of high price over the selected period\n * (period) using predefined Max(dataArray,period) function.\n * Use predefined High() function to obtain the array of high price\n * values.\n */\n\nvar max = Max(High(),n);\n\n/* Calculate minimum values of low price over the selected period\n * using predefined Min(dataArray,period) function.\n * Use predefined Low() function to obtain the array of low price\n * values.\n */\n\nvar min = Min(Low(),n);\n\n/* Obtain the array of closing price values using predefined Close()\n * function and store these values in the 'close' variable.\n */\n\nvar close = Close();\n\n/* Create the array to store calculated %R values for every trading\n * session. To perform this operation, use predefined CreateArray(length)\n * function.\n * Note that each element of the created array will be initially filled\n * with zero value.\n */\n\nvar percentR = CreateArray(close.length);\n\n/* For each trading session, calculate %R value and store that value in\n * 'percentR' array.\n */\n\nfor(var i=0; i<close.length; i++)\n\tif(max[i]-min[i] != 0)\n\t\tpercentR[i] = 100.0 * (close[i]-min[i]) / (max[i]-min[i]);\n\telse\n\t\tpercentR[i] = 100.0;\n\n/* Take the array of %R values, and add graph for that array using\n * predefined function AddGraph(dataArray, firstValidIndex)\n * Note the value of the first valid index.\n */\n\nAddGraph(percentR,n);\n\n/* Take the values of the second and third parameter (Overbought and\n * Oversold levels) and add corresponding horizontal lines to the %R\n * chart.\n */\n\nAddHorizLine(Param(2));\nAddHorizLine(Param(3));\n\n/* End of user-defined %R  */\n\n\n-------------------------------------------------------------------------\n          THE SHORT DESCRIPTION OF INDICATOR DEFINITION RULES\n-------------------------------------------------------------------------\nIndicator algorithms must conform to the following rules:\n\n-they must constitute valid JavaScript code\n\n-the following predefined JavaScript functions may be used to\n simplify indicator construction:\n Open(), High(), Low(), Close(), Volume(), OpenInt(), Param(number),\n CreateArray(length), Min(dataArray,period), Max(dataArray,period),\n ExpAvg(dataArray,period), SimpleAvg(dataArray,period), AddBuySignal(n),\n AddSellSignal(n), StdDev(dataArray,period)\n\n-to add graphs to the indicator area, the following predefined functions\n must be used:\n AddGraph(dataArray,firstValidIndex), AddGraph(dataArray)\n The length of the 'dataArray' argument used in AddGraph function must be\n equal to the length of the array obtained by using such functions as\n Open(), High(), Low(), Close(), Volume(), OpenInt()\n\n-for every indicator calculation, AddGraph function must be used 1, 2, or\n 3 times\n\n-to add a horizontal line to the indicator area, the AddHorizLine(value)\n function must be used\n\n-you cannot comment JavaScript code using // string. Use /* ... */\n construct instead.\n\n-the following predefined functions cannot be used in indicator code:\n __checkParams(), __GetValues(), __isIEBrowser()\n They may only be used internally by the FnCharts program.\n\n\n-------------------------------------------------------------------------\n                    THE LIST OF PREDEFINED FUNCTIONS\n-------------------------------------------------------------------------\n\nOpen()\n    Arguments: none\n    Returns the array of opening price values for the selected symbol.\n\nHigh()\n    Arguments: none\n    Returns the array of high price values for the selected symbol.\n\nLow()\n    Arguments: none\n    Returns the array of low price values for the selected symbol.\n\nClose()\n    Arguments: none\n    Returns the array of closing price values for the selected symbol.\n\nVolume()\n    Arguments: none\n    Returns the array of volume values for the selected symbol.\n\nOpenInt()\n    Arguments: none\n    Returns the array of open interest values for the selected symbol.\n\nImportant information regarding above functions:\n1.  Arrays in JavaScript are indexed from 0 to length-1, so the first\n    element of an array A is A[0] and the last element is A[A.length-1].\n    The first element of the array contains the oldest available data, and\n    the last element of the array contains the most recent data.\n2.  All afore-mentioned functions always return arrays of the same length.\n    For instance, the length of an array returned by Close() function is\n    the same, as the length of an array returned by Volume() function.\n3.  Arrays returned by Open(), High(), Low() and Close() will always\n    contain positive values (values greater than zero).\n4.  If there is no Open, High or Low data available for the selected\n    symbol, then arrays returned by Open(), High() and Low() functions\n    will contain Close values.\n5.  If there is no volume or open interest data available for the\n    selected symbol, arrays returned by Volume() or OpenInt() functions\n    will contain values 0.\n\n\nMax(array,period)\n    Arguments: array - the array for which Max values will be calculated,\n               period - the period used in calculations\n    Calculates maximum value over the given period.\n    Returns the array wherein value of every element is calculated as:\n    value[i] = max(array[i],array[i-1],...,array[i-(period-1)])\n\nMin(array,period)\n    Arguments: array - the array for which Min values will be calculated,\n               period - the period used in calculations\n    Calculates minimum value over the given period.\n    Returns the array wherein value of every element is calculated as:\n    value[i] = min(array[i],array[i-1],...,array[i-(period-1)])\n\nSimpleAvg(array,period)\n    Arguments: array - the array for which average values will be\n                       calculated,\n               period - the period used in calculations\n    Calculates simple average over the given period.\n    Returns the array wherein value of every element is calculated as:\n    value[i] = avg(array[i],array[i-1],...,array[i-(period-1)])\n\nExpAvg(array,period)\n    Arguments: array - the array for which exponential average values\n                       will be calculated,\n               period - the period used in calculations\n    Calculates exponential average over the given period.\n    Returns the array wherein value of every element is calculated as:\n    value[i] = exp_avg(array[i],array[i-1],...,array[i-(period-1)])\n\nStdDev(array,period)\n    Arguments: array - the array for which standard deviation values will\n                       be calculated,\n               period - the period used in calculations\n    Calculates standard deviation over the given period.\n    Returns the array wherein value of every element is calculated as:\n    value[i] = std_dev(array[i],array[i-1],...,array[i-(period-1)])\n\nCreateArray(length)\n    Arguments: length - the length of the array to be created\n    Returns new array of the given length with every element initialized\n    to zero.\n\nParam(n)\n    Arguments: n - parameter number\n    Returns the actual value of the given indicator parameter.\n    Argument 'n' must be in range between 1 and 3.\n    If the value of the given parameter 'n' is not defined then the\n    function returns the value of 0.\n\nAddGraph(array,index)\n    Arguments: array - the array of data,\n               index - index of the first array element containing valid\n                       data\n    Adds a new graph to the indicator area. The graph is constructed\n    based on the data contained in the given array.\n    The length of the array must be exactly the same as the length of the\n    array returned by such functions as Close(), Volume(), etc.\n    Only the part of the graph that represents array elements with\n    indices greater than or equal to 'index' will be displayed.\n    The 'index' parameter is optional. If omitted, the function call\n    AddGraph(array) is equivalent to AddGraph(array,0).\n\nAddHorizLine(value)\n    Arguments: value - the y coordinate of the horizontal line.\n    Adds a horizontal line to the indicator area.\n\nAddBuySignal(index)\n    Arguments: index - the index of the trading session on which the\n                       signal occurred\n    Adds a new buy signal mark to the indicator area.\n\nAddSellSignal(index)\n    Arguments: index - the index of the trading session on which the\n                       signal occurred\n    Adds a new sell signal mark to the indicator area.\n\n\n-------------------------------------------------------------------------\n                       PERFORMANCE RELATED TIPS\n-------------------------------------------------------------------------\n\nFor the low number of trading sessions or transactions (up to 600),\nthe speed of calculations of user defined indicators should be greatest\nunder Microsoft Internet Explorer browser.\nIf greater number of data is involved, then Netscape Navigator or Mozilla\nbrowsers should yield better performance.\n\n\n-------------------------------------------------------------------------\n                  ERROR MESSAGES RELATED INFORMATION\n-------------------------------------------------------------------------\n\nIf any indicator algorithm contains bugs, then the most descriptive\nerror messages will be given when using Microsoft Internet Explorer\nbrowser and Microsoft VM.\n\nRemember, however, that error messages can contain the following\ninaccuracies (due to the limitations of JavaScript and FnCharts\ncollaboration):\n- if you use Microsoft Internet Explorer with Microsoft VM, then the\n  information about error position may be off by 4 characters\n- if you use Microsoft Internet Explorer with other Java Virtual Machine,\n  then the information about error line number may be incorrect.\n\n-------------------------------------------------------------------------\nInternet Explorer is a registered trademark of Microsoft Corporation.\nNetscape Navigator is a registered trademark of Netscape Communications\nCorporation.\nJava and JavaScript are registered trademarks of Sun Microsystems\nCorporation.\n-------------------------------------------------------------------------\n");
    }
    
    public String b(final String s) {
        if (s != null && this.Ssa.containsKey(s)) {
            return this.Ssa.get(s);
        }
        return s;
    }
    
    public String[] n() {
        final Enumeration<String> keys = this.Ssa.keys();
        final String[] array = new String[this.Ssa.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = keys.nextElement();
        }
        return array;
    }
    
    public void _(final String s, final String s2) {
        if (s == null || s2 == null) {
            return;
        }
        this.Ssa.put(s, s2);
    }
}
