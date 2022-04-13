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
                    <th class="application_code">社員番号</th>
                    <th class="application_name">氏名</th>
                    <th class="application_date">日付</th>
                    <th class="application_action">操作</th>
                </tr>
                <c:forEach var="application" items="${applications}" varStatus="status">
                    <fmt:parseDate value="${application.applicationDate}" pattern="yyyy-MM-dd" var="applicationDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="application_code"><c:out value="${application.employee.code}" /></td>
                        <td class="application_name"><c:out value="${application.employee.name}" /></td>
                        <td class="application_date"><fmt:formatDate value='${applicationtDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="application_action">
                            <c:choose>
                                <c:when test="${application.permitFlag == AttributeConst.PERMIT_FLAG_TRUE.getIntegerValue()}">
                                    （済）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='?action=${actAPP}&command=${commShow}&id=${application.id}' />">詳細を見る</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${applications_count} 件) <br />
            <c:forEach var="i" begin="1" end="${((applications_count - 1) / maxRow) + 1}" step="1">
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