<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>


<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>勤怠　一覧</h2>
        <table id="attendance_list">
            <tbody>
                <tr>
                    <th class="attendance_date">日付</th>
                    <th class="attendance_in">出勤出勤</th>
                    <th class="attendance_out">退勤時間</th>
                    <th class="attendance_actualtime">実働時間</th>
                    <th class="attendance_overtime">残業時間</th>
                    <th class="attendance_permit">承認</th>
                </tr>
                <c:forEach var="attendance" items="${attendances}" varStatus="status">
                    <fmt:parseDate value="${attendance.attendanceDate}" pattern="MM/dd" var="attendanceDay" type="date" />
                    <fmt:parseDate value="${attendance.attendanceIn}" pattern="HH:mm:ss" var="attendanceIn" type="date" />
                    <fmt:parseDate value="${attendance.attendanceOut}" pattern="HH:mm:ss" var="attendanceOut" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="attendance_date"><fmt:formatDate value='${attendanceDay}' pattern='MM/dd' /></td>
                        <td class="attendance_in"><fmt:formatDate value='${attendanceIn}' pattern='HH:mm:ss' /></td>
                        <td class="attendance_out"><fmt:formatDate value='${attendanceOut}' pattern='HH:mm:ss' /></td>
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
