<%@ page import="java.text.*, pms.beans.*, java.util.*" contentType="text/html; charset=utf-8"%>

<script language="javascript">
<!--
function goUrl(url){
	window.alert("aaa");
	location.href = url;

	document.getElementById("td1").setAttribute("className", "menu2");
	document.getElementById("td1").background = "/images/menu2_off.gif";
	
	window.alert("bbb");
}
function showTable(tableIdx, tdIdx){
	var tableList = document.getElementsByName("contentTable");
	var tdList = document.getElementsByName("categoryTd");
	
	for(var i=0; i < tableList.length; i++){
		if(i == tableIdx){
			tableList[i].style.display = "";
		}else{
			tableList[i].style.display = "none";
		}
	}
	
	
	
	for(var i= 0; i < tdList.length; i++){
		if(i == tdIdx){
			tdList[i].className = "menu2";
			tdList[i].background = "/images/menu2_off.gif";
			tdList[i].onmouseover = "this.background = \'/images/menu2_on.gif\'";
			tdList[i].onmouseout = "this.background = \'/images/menu2_off.gif\'";
		}else{
			tdList[i].className = "menu1";
			tdList[i].background = "/images/menu1_off.gif";
			tdList[i].onmouseover = "this.background = \'/images/menu1_on.gif\'";
			tdList[i].onmouseout = "this.background = \'/images/menu1_off.gif\'";
		}
	}
}

function showLevel1(showName){
	
	document.getElementById("table1").style.display = "none";
	document.getElementById("table2").style.display = "none";
	document.getElementById("table3").style.display = "none";
	document.getElementById("table4").style.display = "none";
	document.getElementById("table5").style.display = "none";
	
	document.getElementById(showName).style.display = "";
}
-->
</script>

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<!-- Top Menu Start -->
	<tr>
		<td height="70" colspan="2" background="/images/bg_top.jpg" class="txt_11px" style="text-align:right; padding-right:20px">
			<img src="/images/icon_user.gif" width="12" height="12" hspace="2" style="vertical-align:middle">
			<span style="color:#c1cad0"><%=name%>님</span>
			<img src="/images/line_top.gif" width="19" height="12" style="vertical-align:middle">
			<a href="/logout.do"><span style="color:#ceac41">로그아웃</span></a>
			</td>
	</tr>
	<!-- Top Menu End -->
	<!-- Top Navi Start -->
<%
	Vector<CategoryBean> categoriesVec = (Vector<CategoryBean>)request.getAttribute("categories");
	String command = (String)request.getAttribute("command");
	String navigationTitle = "";
	String commandTitle = "";
	int	selectedType = -1;
	
	if(command != null && !command.isEmpty()){
		for(int i = categoriesVec.size() - 1; i >= 0; i--){
			CategoryBean category = categoriesVec.get(i);
			
			if(category.getUrl() != null && category.getUrl().compareTo(command) == 0){
				navigationTitle = category.getName();
				commandTitle = category.getName();
				selectedType = category.getType();
			}
			
			if(category.getLevel() == 0 && category.getType() == selectedType){
				if(!navigationTitle.isEmpty()){
					navigationTitle = category.getName() + " >" + navigationTitle;
				}else{
					navigationTitle = category.getName();
				}
			}
		}
	}
%>	
	
	<tr>
<!-- 
		<td height="30" colspan="2" background="/images/bg_navi.gif" class="txt_11px_white" style="text-align:right; padding-right:20px">성능관리 &gt; MAS 성능감시</td>
-->
		<td height="30" colspan="2" background="/images/bg_navi.gif" class="txt_11px_white" style="text-align:right; padding-right:20px"><%=navigationTitle %></td>
	</tr>
	<!-- Top Navi End -->
	<tr>
		<td width="200" valign="top" background="/images/bg_menu.gif">
			<!-- Left Menu Start -->
			<table  width="200" border="0" cellspacing="0" cellpadding="0">
	
<%
		int tableIndex = 0;
		int tdIndex = 0;
		int imgIndex = 1;   
		boolean createdTable = false;

		for(int i = 0; i < categoriesVec.size(); i++){
			CategoryBean category = categoriesVec.get(i);
			
			if(category.getLevel() == 0){
				if(createdTable){
					out.println("</table></td></tr>");
				}
				
				createdTable = false;
				
				if(category.getUrl() != null && !category.getUrl().isEmpty())
					out.println("<tr onclick=\"location.href=\'" + category.getUrl() + "\'\">");
				else{
					out.println("<tr onclick=\"showTable(" + "\'" + String.valueOf(tableIndex) + "\'" + ", \'" + String.valueOf(tdIndex) + "\'"  + ")\">");
					tableIndex++;
				}
				
				out.println(String.format("<td id=\"categoryTd\" class=\"%s\" background=\"%s\" onmouseover=\"this.background=\'%s\'\" onmouseout=\"this.background=\'%s\'\"><img src=\"/images/icon_menu%d.png\" width=\"16\" height=\"16\" hspace=\"5\" style=\"vertical-align:middle\">%s</td></tr>",
						(category.getType() == selectedType ? "menu2" : "menu1"),
						(category.getType() == selectedType ? "/images/menu2_off.gif" : "/images/menu1_off.gif"),
						(category.getType() == selectedType ? "/images/menu2_on.gif" : "/images/menu1_on.gif"),
						(category.getType() == selectedType ? "/images/menu2_off.gif" : "/images/menu1_off.gif"),
						imgIndex,
						category.getName()));
				
				tdIndex++;
				imgIndex++;
				
			}else if(category.getLevel() == 1){
				if(!createdTable){
					createdTable = true;
					out.println("<tr><td><table id=\"contentTable\" width=\"200\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"" + (category.getType() == selectedType ? "" : " style=\"display:none\" ") + ">");
				}
				
				if(category.getShow() == 0){
					continue;
				}
				
				if(category.getUrl() != null && !category.getUrl().isEmpty()){
					out.println("<tr onclick=\"location.href = \'"  + category.getUrl()  + "\' \">");
					if(category.getUrl().compareTo(command) == 0){
						out.println("<td class=\"menu4\" background=\"/images/menu4_off.gif\" onmouseover=\"this.background=\'/images/menu4_on.gif\'\" onmouseout=\"this.background=\'/images/menu4_off.gif\'\">" + category.getName() + "</td></tr>");
					}else
						out.println("<td class=\"menu3\" background=\"/images/menu3_off.gif\" onmouseover=\"this.background=\'/images/menu3_on.gif\'\" onmouseout=\"this.background=\'/images/menu3_off.gif\'\" >" + category.getName() + "</td></tr>");
				}else{
					out.println("<tr ><td class=\"menu3\" background=\"/images/menu3_off.gif\" onmouseover=\"this.background=\'/images/menu3_on.gif\'\" onmouseout=\"this.background=\'/images/menu3_off.gif\'\" >" + category.getName() + "</td></tr>");
				}
			}
		}
%>		</table></td></tr>
			</table>

		</td>
		<td valign="top" style="padding:20px">	
			
<!--			
				<tr onclick="location.href='/'">
					<td id="td1"  class="menu1" background="/images/menu1_off.gif" onmouseover="this.background='/images/menu1_on.gif'" onmouseout="this.background='/images/menu1_off.gif'"><img src="/images/icon_menu1.png" width="16" height="16" hspace="5" style="vertical-align:middle">모니터링</td>
				</tr>






				<tr onclick="showLevel1('table1')" >
					<td class="menu1" background="/images/menu1_off.gif" onmouseover="this.background='/images/menu1_on.gif'" onmouseout="this.background='/images/menu1_off.gif'"><img src="/images/icon_menu2.png" width="16" height="16" hspace="5" style="vertical-align:middle">서비스상태관리</td>
				</tr>
				<tr>
					<td>
						<table id="table1" width="200" border="0" cellpadding="0" cellspacing="0">
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >고객별 발송상태</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >고객별 세션상태</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >서버군 발송상태</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >전송결과 조회</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >결과코드 조회</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >장애보고</td>
							</tr>
						</table>
					</td>
				</tr>
				
				
				
				
				
				
				
				
				
				
				
				<tr onclick="showLevel1('table2')" >
					<td class="menu2" background="/images/menu2_off.gif" onmouseover="this.background='/images/menu2_on.gif'" onmouseout="this.background='/images/menu2_off.gif'"><img src="/images/icon_menu3.png" width="16" height="16" hspace="5" style="vertical-align:middle">성능관리</td>
				</tr>
				<tr>
					<td>
						<table id="table2" width="200" border="0" cellpadding="0" cellspacing="0">
							<tr onclick="location.href = '/perf/cid.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >CID별 발송상태</td>
							</tr>
							<tr onclick="location.href = '/perf/rcs.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">RCS 성능감시태</td>
							</tr>
							<tr onclick="location.href = '/perf/mas.do' ">
								<td class="menu4" background="/images/menu4_off.gif" onmouseover="this.background='/images/menu4_on.gif'" onmouseout="this.background='/images/menu4_off.gif'">MAS 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/mrs.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">MRS 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/scs.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">SCS 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/tms.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">TMS 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/tgs.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">TGS 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/gateway.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">Relay(G/W) 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/fus.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">FUS 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/fcs/tts.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">FCS_TTS 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/fcs/ttf.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">FCS_TTF 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/api.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">API 서버 성능감시</td>
							</tr>
							<tr onclick="location.href = '/perf/npdb.do' ">
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'">NPDB 상태 감시</td>
							</tr>
						</table>
					</td>
				</tr>
				
				
				
				
				
				
				
				
				
				
				
				<tr onclick="showLevel1('table3')" >
					<td class="menu1" background="/images/menu1_off.gif" onmouseover="this.background='/images/menu1_on.gif'" onmouseout="this.background='/images/menu1_off.gif'"><img src="/images/icon_menu4.png" width="16" height="16" hspace="5" style="vertical-align:middle">설정관리</td>
				</tr>
				
				<tr>
					<td>
						<table id="table3" width="200" border="0" cellpadding="0" cellspacing="0">
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >VCID 관리</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >이벤트 수신자 관리</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >고객별 설정</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >기능별 설정</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >장애알림 메시지 발송</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >메시지 발송</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >전송결과 조회</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >발송대상자 관리</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >관심고객 설정</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >장비관리</td>
							</tr>
						</table>
					</td>
				</tr>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				<tr onclick="showLevel1('table4')" >
					<td class="menu1" background="/images/menu1_off.gif" onmouseover="this.background='/images/menu1_on.gif'" onmouseout="this.background='/images/menu1_off.gif'"><img src="/images/icon_menu5.png" width="16" height="16" hspace="5" style="vertical-align:middle">스팸관리</td>
				</tr>
				<tr>
					<td>
						<table id="table4" width="200" border="0" cellpadding="0" cellspacing="0">
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >B2C 모니터링</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >B2B 모니터링</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >스팸필터링 관리</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >스팸IP 관리</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >스팸관리대장</td>
							</tr>
						</table>
					</td>
				</tr>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				<tr onclick="showLevel1('table5')" >
					<td class="menu1" background="/images/menu1_off.gif" onmouseover="this.background='/images/menu1_on.gif'" onmouseout="this.background='/images/menu1_off.gif'"><img src="/images/icon_menu6.png" width="16" height="16" hspace="5" style="vertical-align:middle">관리자정보</td>
				</tr>
				<tr>
					<td>
						<table id="table5" width="200" border="0" cellpadding="0" cellspacing="0">
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >관리자등록</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >관리자권한설정</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >관리이력</td>
							</tr>
							<tr >
								<td class="menu3" background="/images/menu3_off.gif" onmouseover="this.background='/images/menu3_on.gif'" onmouseout="this.background='/images/menu3_off.gif'" >관리자접속현황</td>
							</tr>
						</table>
					</td>
				</tr>


				
				
				
				
				
			</table>

		</td>
		<td valign="top" style="padding:20px">
-->		