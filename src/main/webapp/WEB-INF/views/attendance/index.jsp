<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actAPP" value="${ForwardConst.ACT_APP.getValue()}" />
<c:set var="actEmp" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="actATT" value="${ForwardConst.ACT_ATT.getValue()}" />

<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>勤怠　一覧 </h2>
        <div>
            <button type="button" onclick="location.href='?action=${actATT}&command=${commNew}'" >打刻</button>
            <button type="button" onclick="location.href='?action=${actAPP}&command=${commNew}'" >申請</button>
        </div>
        <table id="attendance_list">
            <tbody>
                <tr>
                    <th class="attendance_date">日付</th>
                    <th class="attendance_start">出勤出勤</th>
                    <th class="attendance_finish">退勤時間</th>
                    <th class="attendance_actualtime">実働時間</th>
                    <th class="attendance_overtime">残業時間</th>
                    <th class="attendance_permit">承認</th>
                </tr>
                <c:forEach var="attendance" items="${attendances}" varStatus="status">
                    <fmt:parseDate value="${attendance.attendanceDate}" pattern="MM/dd" var="attendanceDay" type="date" />
                    <fmt:parseDate value="${attendance.attendanceStart}" pattern="HH:mm:ss" var="attendanceStart" type="date" />
                    <fmt:parseDate value="${attendance.attendanceFinish}" pattern="HH:mm:ss" var="attendanceFinish" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="attendance_date"><fmt:formatDate value='${attendanceDay}' pattern='MM/dd' /></td>
                        <td class="attendance_start"><fmt:formatDate value='${attendanceStart}' pattern='HH:mm:ss' /></td>
                        <td class="attendance_finish"><fmt:formatDate value='${attendanceFinish}' pattern='HH:mm:ss' /></td>
                        <td class="attendance_actualtime"></td>
                        <td class="attendance_overtime"></td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.permitFlag == AttributeConst.PERMIT_TRUE.getIntegerValue()}">
                                    済
                                </c:when>
                                <c:when test="${sessionScope.permitFlag == AttributeConst.PERMIT_FALSE.getIntegerValue()}">
                                    未
                                </c:when>
                                <c:otherwise>
                                    <br>
                                </c:otherwise>
                            </c:choose>
                        </td>
                </c:forEach>
            </tbody>
        </table>
    </c:param>
</c:import>
