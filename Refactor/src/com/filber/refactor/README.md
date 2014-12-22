<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 14">
<link rel=File-List href="Refactorings.files/filelist.xml">
<style id="Refactorings_32061_Styles">
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
.font032061
	{color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;}
.font532061
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;}
.font632061
	{color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.xl1532061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6332061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6432061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border-top:1.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6532061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border-top:1.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6632061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border-top:1.5pt solid windowtext;
	border-right:1.5pt solid windowtext;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6732061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border-top:none;
	border-right:1.5pt solid windowtext;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6832061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border-top:none;
	border-right:none;
	border-bottom:1.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6932061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border-top:none;
	border-right:none;
	border-bottom:1.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7032061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border-top:none;
	border-right:1.5pt solid windowtext;
	border-bottom:1.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7132061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:left;
	vertical-align:bottom;
	border-top:1.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:1.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7232061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:left;
	vertical-align:bottom;
	border-top:none;
	border-right:none;
	border-bottom:none;
	border-left:1.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7332061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:left;
	vertical-align:bottom;
	border-top:none;
	border-right:none;
	border-bottom:1.5pt solid windowtext;
	border-left:1.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7432061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:1.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7532061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7632061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:none;
	border-bottom:1.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7732061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:1.5pt solid windowtext;
	background:#C5D9F1;
	mso-pattern:black none;
	white-space:nowrap;}
.xl7832061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:12.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:1.5pt solid windowtext;
	border-right:1.5pt solid windowtext;
	border-bottom:none;
	border-left:1.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;
	mso-rotate:90;}
.xl7932061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:12.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:none;
	border-right:1.5pt solid windowtext;
	border-bottom:none;
	border-left:1.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;
	mso-rotate:90;}
.xl8032061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:12.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:none;
	border-right:1.5pt solid windowtext;
	border-bottom:1.5pt solid windowtext;
	border-left:1.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;
	mso-rotate:90;}
.xl8132061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:12.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:none;
	border-right:1.5pt solid windowtext;
	border-bottom:none;
	border-left:1.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;
	mso-rotate:90;}
.xl8232061
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:12.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:none;
	border-right:1.5pt solid windowtext;
	border-bottom:1.5pt solid windowtext;
	border-left:1.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;
	mso-rotate:90;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Tahoma, sans-serif;
	mso-font-charset:0;
	mso-char-type:none;}
-->
</style>
</head>

<body>
<!--[if !excel]>　　<![endif]-->
<!--下列信息由 Microsoft Excel 的发布为网页向导生成。-->
<!--如果同一条目从 Excel 中重新发布，则所有位于 DIV 标记之间的信息均将被替换。-->
<!----------------------------->
<!--“从 EXCEL 发布网页”向导开始-->
<!----------------------------->

<div id="Refactorings_32061" align=center x:publishsource="Excel">

<table border=0 cellpadding=0 cellspacing=0 width=1036 style='border-collapse:
 collapse;table-layout:fixed;width:778pt'>
 <col width=198 style='mso-width-source:userset;mso-width-alt:6336;width:149pt'>
 <col width=82 style='mso-width-source:userset;mso-width-alt:2624;width:62pt'>
 <col width=107 style='mso-width-source:userset;mso-width-alt:3424;width:80pt'>
 <col width=348 style='mso-width-source:userset;mso-width-alt:11136;width:261pt'>
 <col width=229 style='mso-width-source:userset;mso-width-alt:7328;width:172pt'>
 <col width=72 style='mso-width-source:userset;mso-width-alt:2304;width:54pt'>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7732061 width=198 style='height:15.95pt;width:149pt'><a
  name="RANGE!A1:F73">Category</a></td>
  <td class=xl7732061 width=82 style='border-left:none;width:62pt'>Index</td>
  <td class=xl7732061 width=107 style='border-left:none;width:80pt'>PDF Page</td>
  <td class=xl7732061 width=348 style='border-left:none;width:261pt'>Refactoring</td>
  <td class=xl7732061 width=229 style='border-left:none;width:172pt'>Name</td>
  <td class=xl7732061 width=72 style='border-left:none;width:54pt'>Book Page</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td rowspan=9 height=189 class=xl7832061 style='border-bottom:1.5pt solid black;
  height:143.55pt;border-top:none'>重新组织函数</td>
  <td class=xl7132061 style='border-top:none;border-left:none'>1</td>
  <td class=xl7432061 style='border-top:none'>135</td>
  <td class=xl6432061 style='border-top:none'>Extract Method</td>
  <td class=xl6532061 style='border-top:none'>提炼函数</td>
  <td class=xl6632061 align=right style='border-top:none'>110</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>2</td>
  <td class=xl7532061>142</td>
  <td class=xl1532061>Inline Method</td>
  <td class=xl6332061>将函数内联化</td>
  <td class=xl6732061 align=right>117</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>3</td>
  <td class=xl7532061>144</td>
  <td class=xl1532061>Inline Temp</td>
  <td class=xl6332061>将临时变量内联化</td>
  <td class=xl6732061 align=right>119</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>4</td>
  <td class=xl7532061>145</td>
  <td class=xl1532061>Replace Temp With Query</td>
  <td class=xl6332061>以查询取代临时变量</td>
  <td class=xl6732061 align=right>120</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>5</td>
  <td class=xl7532061>149</td>
  <td class=xl1532061>Introduce Explaining Variable</td>
  <td class=xl6332061>引入解释性变量</td>
  <td class=xl6732061 align=right>124</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>6</td>
  <td class=xl7532061>153</td>
  <td class=xl1532061>Split Temporary Variable</td>
  <td class=xl6332061>剖解临时变量</td>
  <td class=xl6732061 align=right>128</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>7</td>
  <td class=xl7532061>156</td>
  <td class=xl1532061>Remove Assignments to Parameters</td>
  <td class=xl6332061>移除对参数的赋值动作</td>
  <td class=xl6732061 align=right>131</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>8</td>
  <td class=xl7532061>160</td>
  <td class=xl1532061>Replace Method With Method Object</td>
  <td class=xl6332061>以函数对象取代函数</td>
  <td class=xl6732061 align=right>135</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7332061 style='height:15.95pt;border-left:none'>9</td>
  <td class=xl7632061>164</td>
  <td class=xl6832061>Substitute Algorithm</td>
  <td class=xl6932061>替换算法</td>
  <td class=xl7032061 align=right>139</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td rowspan=8 height=168 class=xl7832061 style='border-bottom:1.5pt solid black;
  height:127.6pt;border-top:none'>在对象之间搬移特性</td>
  <td class=xl7132061 style='border-top:none;border-left:none'>10</td>
  <td class=xl7432061 style='border-top:none'>167</td>
  <td class=xl6432061 style='border-top:none'>Move Method</td>
  <td class=xl6532061 style='border-top:none'>搬移函数</td>
  <td class=xl6632061 align=right style='border-top:none'>142</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>11</td>
  <td class=xl7532061>171</td>
  <td class=xl1532061>Move Field</td>
  <td class=xl6332061>搬移值域</td>
  <td class=xl6732061 align=right>146</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>12</td>
  <td class=xl7532061>174</td>
  <td class=xl1532061>Extract Class</td>
  <td class=xl6332061>提炼类</td>
  <td class=xl6732061 align=right>149</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>13</td>
  <td class=xl7532061>179</td>
  <td class=xl1532061>Inline Class</td>
  <td class=xl6332061>将类内联化</td>
  <td class=xl6732061 align=right>154</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>14</td>
  <td class=xl7532061>182</td>
  <td class=xl1532061>Hide Delegate</td>
  <td class=xl6332061>隐藏&quot;委托关系&quot;</td>
  <td class=xl6732061 align=right>157</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>15</td>
  <td class=xl7532061>185</td>
  <td class=xl1532061>Remove Middle Man</td>
  <td class=xl6332061>移除中间人</td>
  <td class=xl6732061 align=right>160</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>16</td>
  <td class=xl7532061>187</td>
  <td class=xl1532061>Introduce Foreign Method</td>
  <td class=xl6332061>引入外加函数</td>
  <td class=xl6732061 align=right>162</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7332061 style='height:15.95pt;border-left:none'>17</td>
  <td class=xl7632061>189</td>
  <td class=xl6832061>Introduce Local Extension</td>
  <td class=xl6932061>引入本地扩展</td>
  <td class=xl7032061 align=right>164</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td rowspan=16 height=336 class=xl7832061 style='border-bottom:1.5pt solid black;
  height:255.2pt;border-top:none'>重新组织数据</td>
  <td class=xl7132061 style='border-top:none;border-left:none'>18</td>
  <td class=xl7432061 style='border-top:none'>196</td>
  <td class=xl6432061 style='border-top:none'>Self Encapsulate Field</td>
  <td class=xl6532061 style='border-top:none'>自封装值域</td>
  <td class=xl6632061 align=right style='border-top:none'>171</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>19</td>
  <td class=xl7532061>200</td>
  <td class=xl1532061>Replace Data Value with Object</td>
  <td class=xl6332061>以对象取代数据值</td>
  <td class=xl6732061 align=right>175</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>20</td>
  <td class=xl7532061>204</td>
  <td class=xl1532061>Change Value to Reference</td>
  <td class=xl6332061>将实值对象改为引用对象</td>
  <td class=xl6732061 align=right>179</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>21</td>
  <td class=xl7532061>208</td>
  <td class=xl1532061>Change Reference to Value</td>
  <td class=xl6332061>将引用对象改为实值对象</td>
  <td class=xl6732061 align=right>183</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>22</td>
  <td class=xl7532061>211</td>
  <td class=xl1532061>Replace Array With Object</td>
  <td class=xl6332061>以对象取代数组</td>
  <td class=xl6732061 align=right>186</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>23</td>
  <td class=xl7532061>214</td>
  <td class=xl1532061>Duplicate Observed Data</td>
  <td class=xl1532061><font class="font632061">复制</font><font class="font032061">&quot;</font><font
  class="font632061">被监视数据</font><font class="font032061">&quot;</font></td>
  <td class=xl6732061 align=right>189</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>24</td>
  <td class=xl7532061>222</td>
  <td class=xl1532061>Change Unidirectional Association to Bidirectional</td>
  <td class=xl6332061>将单向关联改为双向</td>
  <td class=xl6732061 align=right>197</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>25</td>
  <td class=xl7532061>225</td>
  <td class=xl1532061>Change Bidirectional Association to Unidirectional</td>
  <td class=xl6332061>将双向关联改为单向</td>
  <td class=xl6732061 align=right>200</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>26</td>
  <td class=xl7532061>229</td>
  <td class=xl1532061>Replace Magic Number with Symbolic Constant</td>
  <td class=xl6332061>以字面常量取代魔法数</td>
  <td class=xl6732061 align=right>204</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>27</td>
  <td class=xl7532061>231</td>
  <td class=xl1532061>Encapsulate Field</td>
  <td class=xl6332061>封装值域</td>
  <td class=xl6732061 align=right>206</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>28</td>
  <td class=xl7532061>233</td>
  <td class=xl1532061>Encapsulate Collection</td>
  <td class=xl6332061>封装群集</td>
  <td class=xl6732061 align=right>208</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>29</td>
  <td class=xl7532061>242</td>
  <td class=xl1532061>Replace Record with Data Class</td>
  <td class=xl6332061>以数据类取代记录</td>
  <td class=xl6732061 align=right>217</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>30</td>
  <td class=xl7532061>243</td>
  <td class=xl1532061>Replace Type Code with Class</td>
  <td class=xl6332061>以类取代类别码</td>
  <td class=xl6732061 align=right>218</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>31</td>
  <td class=xl7532061>248</td>
  <td class=xl1532061>Replace Type Code with Subclasses</td>
  <td class=xl6332061>以子类取代类别码</td>
  <td class=xl6732061 align=right>223</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>32</td>
  <td class=xl7532061>252</td>
  <td class=xl1532061>Replace Type Code with State/Strategy</td>
  <td class=xl6332061>以State/Strategy取代类别码</td>
  <td class=xl6732061 align=right>227</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7332061 style='height:15.95pt;border-left:none'>33</td>
  <td class=xl7632061>257</td>
  <td class=xl6832061>Replace Subclass with Field</td>
  <td class=xl6932061>以值域取代子类</td>
  <td class=xl7032061 align=right>232</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td rowspan=8 height=168 class=xl7832061 style='border-bottom:1.5pt solid black;
  height:127.6pt;border-top:none'>简化条件表达式</td>
  <td class=xl7132061 style='border-top:none;border-left:none'>34</td>
  <td class=xl7432061 style='border-top:none'>263</td>
  <td class=xl6432061 style='border-top:none'>Decompose Conditional</td>
  <td class=xl6532061 style='border-top:none'>分解条件表达式</td>
  <td class=xl6632061 align=right style='border-top:none'>238</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>35</td>
  <td class=xl7532061>265</td>
  <td class=xl1532061>Consolidate Conditional Expression</td>
  <td class=xl6332061>合并条件式</td>
  <td class=xl6732061 align=right>240</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>36</td>
  <td class=xl7532061>268</td>
  <td class=xl1532061>Consolidate Duplicate Conditional Fragments</td>
  <td class=xl6332061>合并重复的条件片段</td>
  <td class=xl6732061 align=right>243</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>37</td>
  <td class=xl7532061>270</td>
  <td class=xl1532061>Remove Control Flag</td>
  <td class=xl6332061>移除控制标记</td>
  <td class=xl6732061 align=right>245</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>38</td>
  <td class=xl7532061>275</td>
  <td class=xl1532061>Replace Nested Conditional with Guard Clauses</td>
  <td class=xl6332061>以卫语句取代嵌套条件式</td>
  <td class=xl6732061 align=right>250</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>39</td>
  <td class=xl7532061>280</td>
  <td class=xl1532061>Replace Conditional with Polymorphism</td>
  <td class=xl6332061>以多态取代条件式</td>
  <td class=xl6732061 align=right>255</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>40</td>
  <td class=xl7532061>285</td>
  <td class=xl1532061>Introduce Null Object</td>
  <td class=xl6332061>引入Null对象</td>
  <td class=xl6732061 align=right>260</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7332061 style='height:15.95pt;border-left:none'>41</td>
  <td class=xl7632061>292</td>
  <td class=xl6832061>Introduce Assertion</td>
  <td class=xl6932061>引入断言</td>
  <td class=xl7032061 align=right>267</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td rowspan=15 height=315 class=xl7832061 style='height:239.25pt;border-top:
  none'>简化函数调用</td>
  <td class=xl7132061 style='border-top:none;border-left:none'>42</td>
  <td class=xl7432061 style='border-top:none'>298</td>
  <td class=xl6432061 style='border-top:none'>Rename Method</td>
  <td class=xl6532061 style='border-top:none'>重命名函数</td>
  <td class=xl6632061 align=right style='border-top:none'>273</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>43</td>
  <td class=xl7532061>300</td>
  <td class=xl1532061>Add Parameter</td>
  <td class=xl6332061>添加参数</td>
  <td class=xl6732061 align=right>275</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>44</td>
  <td class=xl7532061>302</td>
  <td class=xl1532061>Remove Parameter</td>
  <td class=xl6332061>移除参数</td>
  <td class=xl6732061 align=right>277</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>45</td>
  <td class=xl7532061>304</td>
  <td class=xl1532061>Separate Query from Modifier</td>
  <td class=xl6332061>将查询函数和修改函数分离</td>
  <td class=xl6732061 align=right>279</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>46</td>
  <td class=xl7532061>308</td>
  <td class=xl1532061>Parameterize Method</td>
  <td class=xl6332061>令函数携带参数</td>
  <td class=xl6732061 align=right>283</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>47</td>
  <td class=xl7532061>310</td>
  <td class=xl1532061>Replace Parameter with Explicit Method</td>
  <td class=xl6332061>以明确函数取代参数</td>
  <td class=xl6732061 align=right>285</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>48</td>
  <td class=xl7532061>313</td>
  <td class=xl1532061>Preserve Whole Object</td>
  <td class=xl6332061>保持对象完整</td>
  <td class=xl6732061 align=right>288</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>49</td>
  <td class=xl7532061>317</td>
  <td class=xl1532061>Replace Parameter with Method</td>
  <td class=xl6332061>以函数取代参数</td>
  <td class=xl6732061 align=right>292</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>50</td>
  <td class=xl7532061>320</td>
  <td class=xl1532061>Introduce Parameter Object</td>
  <td class=xl6332061>引入参数对象</td>
  <td class=xl6732061 align=right>295</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>51</td>
  <td class=xl7532061>325</td>
  <td class=xl1532061>Remove Setting Method</td>
  <td class=xl6332061>移除设值函数</td>
  <td class=xl6732061 align=right>300</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>52</td>
  <td class=xl7532061>328</td>
  <td class=xl1532061>Hide Method</td>
  <td class=xl6332061>隐藏函数</td>
  <td class=xl6732061 align=right>303</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>53</td>
  <td class=xl7532061>329</td>
  <td class=xl1532061>Replace Constructor with Factory Method</td>
  <td class=xl6332061>以工程方法取代构造函数</td>
  <td class=xl6732061 align=right>304</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>54</td>
  <td class=xl7532061>333</td>
  <td class=xl1532061>Encapsulate Downcast</td>
  <td class=xl6332061>封装&quot;向下转型&quot;动作</td>
  <td class=xl6732061 align=right>308</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>55</td>
  <td class=xl7532061>335</td>
  <td class=xl1532061>Replace Error Code with Exception</td>
  <td class=xl6332061>以异常取代错误码</td>
  <td class=xl6732061 align=right>310</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7332061 style='height:15.95pt;border-left:none'>56</td>
  <td class=xl7632061>340</td>
  <td class=xl6832061>Replace Exception with Test</td>
  <td class=xl6932061>以测试取代异常</td>
  <td class=xl7032061 align=right>315</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td rowspan=12 height=252 class=xl7832061 style='height:191.4pt'>处理概括关系</td>
  <td class=xl7132061 style='border-top:none;border-left:none'>57</td>
  <td class=xl7432061 style='border-top:none'>345</td>
  <td class=xl6432061 style='border-top:none'>Pull Up Field</td>
  <td class=xl6532061 style='border-top:none'>值域上拉</td>
  <td class=xl6632061 align=right style='border-top:none'>320</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>58</td>
  <td class=xl7532061>347</td>
  <td class=xl1532061>Pull Up Method</td>
  <td class=xl6332061>函数上拉</td>
  <td class=xl6732061 align=right>322</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>59</td>
  <td class=xl7532061>350</td>
  <td class=xl1532061>Pull Up Constructor Body</td>
  <td class=xl6332061>构造函数本体上移</td>
  <td class=xl6732061 align=right>325</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>60</td>
  <td class=xl7532061>353</td>
  <td class=xl1532061>Push Down Method</td>
  <td class=xl6332061>函数下移</td>
  <td class=xl6732061 align=right>328</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>61</td>
  <td class=xl7532061>354</td>
  <td class=xl1532061>Push Down Field</td>
  <td class=xl6332061>值域下移</td>
  <td class=xl6732061 align=right>329</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>62</td>
  <td class=xl7532061>355</td>
  <td class=xl1532061>Extract Subclass</td>
  <td class=xl6332061>提炼子类</td>
  <td class=xl6732061 align=right>330</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>63</td>
  <td class=xl7532061>361</td>
  <td class=xl1532061>Extract Superclass</td>
  <td class=xl6332061>提炼超类</td>
  <td class=xl6732061 align=right>336</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>64</td>
  <td class=xl7532061>366</td>
  <td class=xl1532061>Extract Interface</td>
  <td class=xl6332061>提炼接口</td>
  <td class=xl6732061 align=right>341</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>65</td>
  <td class=xl7532061>369</td>
  <td class=xl1532061>Collapse Hierarchy</td>
  <td class=xl6332061>折叠集成体系</td>
  <td class=xl6732061 align=right>344</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>66</td>
  <td class=xl7532061>370</td>
  <td class=xl1532061>Form Template Method</td>
  <td class=xl6332061>塑造模板函数</td>
  <td class=xl6732061 align=right>345</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>67</td>
  <td class=xl7532061>377</td>
  <td class=xl1532061>Replace Inheritance with Delegation</td>
  <td class=xl6332061>以委托取代继承</td>
  <td class=xl6732061 align=right>352</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7332061 style='height:15.95pt;border-left:none'>68</td>
  <td class=xl7632061>380</td>
  <td class=xl6832061>Replace Delegation with Inheritance</td>
  <td class=xl6932061>以继承取代委托</td>
  <td class=xl7032061 align=right>355</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td rowspan=4 height=84 class=xl7832061 style='border-bottom:1.5pt solid black;
  height:63.8pt'>大型重构</td>
  <td class=xl7132061 style='border-top:none;border-left:none'>69</td>
  <td class=xl7432061 style='border-top:none'>387</td>
  <td class=xl6432061 style='border-top:none'>Tease Apart Inheritance</td>
  <td class=xl6532061 style='border-top:none'>梳理并分解继承体系</td>
  <td class=xl6632061 align=right style='border-top:none'>362</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>70</td>
  <td class=xl7532061>393</td>
  <td class=xl1532061>Convert Procedural Design to Objects</td>
  <td class=xl6332061>将过程化设计转化为对象设计</td>
  <td class=xl6732061 align=right>368</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7232061 style='height:15.95pt;border-left:none'>71</td>
  <td class=xl7532061>395</td>
  <td class=xl1532061>Separate Domain from Presentation</td>
  <td class=xl6332061>将领域和表述/显示分离</td>
  <td class=xl6732061 align=right>370</td>
 </tr>
 <tr height=21 style='mso-height-source:userset;height:15.95pt'>
  <td height=21 class=xl7332061 style='height:15.95pt;border-left:none'>72</td>
  <td class=xl7632061>400</td>
  <td class=xl6832061>Extract Hierarchy</td>
  <td class=xl6932061>提炼继承体系</td>
  <td class=xl7032061 align=right>375</td>
 </tr>
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=198 style='width:149pt'></td>
  <td width=82 style='width:62pt'></td>
  <td width=107 style='width:80pt'></td>
  <td width=348 style='width:261pt'></td>
  <td width=229 style='width:172pt'></td>
  <td width=72 style='width:54pt'></td>
 </tr>
 <![endif]>
</table>

</div>


<!----------------------------->
<!--“从 EXCEL 发布网页”向导结束-->
<!----------------------------->
</body>

</html>
