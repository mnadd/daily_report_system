<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actAPP" value="${ForwardConst.ACT_APP.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>申請　一覧</h2>
        <table id="application_list">
            <tbody>
                <tr>
                    <th class="timecardApplication_code">社員番号</th>
                    <th class="timecardApplication_name">氏名</th>
                    <th class="timecardApplication_date">日付</th>
                    <th class="timecardApplication_action">操作</th>
                </tr>
                <c:forEach var="timecardApplication" items="${timecardApplications}" varStatus="status">
                    <fmt:parseDate value="${timecardApplication.timecardApplicationDate}" pattern="yyyy-MM-dd" var="timecardApplicationDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="timecardApplication_code"><c:out value="${timecardApplication.employee.code}" /></td>
                        <td class="timecardApplication_name"><c:out value="${timecardApplication.employee.name}" /></td>
                        <td class="timecardApplication_date"><fmt:formatDate value='${timecardApplicationtDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="timecardApplication_action">
                            <%--<c:choose>
                                <c:when test="${application.permitFlag == AttributeConst.PERMIT_FLAG_TRUE.getIntegerValue()}">
                                    （済）
                                </c:when>
                                <c:otherwise>  --%>
                                    <a href="<c:url value='?action=${actAPP}&command=${commShow}&id=${timecardApplication.id}' />">詳細を見る</a>
                                <%--</c:otherwise>
                            </c:choose> --%>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${timecardApplications_count} 件) <br />
            <c:forEach var="i" begin="1" end="${((timecardApplications_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actAPP}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>