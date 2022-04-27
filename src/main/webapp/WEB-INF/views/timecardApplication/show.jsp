<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="action" value="${ForwardConst.ACT_APP.getValue()}" />
<c:set var="commApp" value="${ForwardConst.CMD_APPROVE.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>申請内容　詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${timecardApplication.employee.name}" /></td>
                </tr>
                <tr>
                    <th>対象日</th>
                    <td><c:out value="${timecardApplication.timecardApplicationDate}" /></td>
                </tr>
                <tr>
                    <th>打刻情報</th>
                    <fmt:parseDate value="${timecardApplication.attendance.start}" pattern="HH:mm" var="start" type="date" />
                    <fmt:parseDate value="${timecardApplication.attendance.finish}" pattern="HH:mm" var="finish" type="date" />
                    <td><fmt:formatDate value='${start}' pattern='HH:mm' /> ～ <fmt:formatDate value='${finish}' pattern='HH:mm' /></td>
                </tr>
                <tr>
                    <th>申請内容</th>
                    <td><c:choose>
                        <c:when test="${timecardApplication.typeFlag == AttributeConst.TYPE_START.getIntegerValue()}">出勤</c:when>
                        <c:otherwise>退勤</c:otherwise>
                        </c:choose></td>
                </tr>
                <tr>
                    <th>修正時刻</th>
                    <td><c:out value="${timecardApplication.time}" /></td>
                </tr>
                <tr>
                    <th>申請理由</th>
                    <td><c:out value="${timecardApplication.appContent}" /></td>
                </tr>
            </tbody>
        </table>


       <form method="POST" action="<c:url value='?action=${action}&command=${commApp}' />">
            <input type="hidden" name="${AttributeConst.APP_ID.getValue()}" value="${timecardApplication.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <button type="submit">承認</button>
        </form>

        <form method="POST" action="<c:url value='?action=${action}&command=${commAppF}' />">
            <label for="${AttributeConst.APP_COMMENT.getValue()}">差し戻し用コメント</label><br />
            <input type="text" name="${AttributeConst.APP_COMMENT.getValue()}" value="${timecardApplication.comment}" />
            <input type="hidden" name="${AttributeConst.APP_ID.getValue()}" value="${timecardApplication.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        </form>

        <p><a href="<c:url value='?action=${action}&command=${commIdx}' />">一覧に戻る</a></p>
    </c:param>
</c:import>