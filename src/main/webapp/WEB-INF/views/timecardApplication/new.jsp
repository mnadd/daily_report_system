<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="action" value="${ForwardConst.ACT_APP.getValue()}" />
<c:set var="actATT" value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>タイムカード修正申請　ページ</h2>

        <form method="POST" action="<c:url value='?action=${action}&command=${commCrt}' />">
            <c:if test="${errors != null}">
                <div id="flush_error">
                    入力内容にエラーがあります。<br />
                     <c:forEach var="error" items="${errors}">
                        ・<c:out value="${error}" /><br />
                     </c:forEach>
                </div>
            </c:if>

<label for="${AttributeConst.APP_TYPE_FLAG.getValue()}">申請内容</label><br />
<select name="${AttributeConst.APP_TYPE_FLAG.getValue()}">
    <option value="${AttributeConst.TYPE_START.getIntegerValue()}"<c:if test="${timecardApplication.typeFlag == AttributeConst.TYPE_START.getIntegerValue()}"> selected</c:if>>出勤</option>
    <option value="${AttributeConst.TYPE_FINISH.getIntegerValue()}"<c:if test="${timecardApplication.typeFlag == AttributeConst.TYPE_FINISH.getIntegerValue()}"> selected</c:if>>退勤</option>
</select>

<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<fmt:parseDate value="${timecardApplication.timecardApplicationDate}" pattern="yyyy-MM-dd" var="applicationDay" type="date" />
<label for="${AttributeConst.APP_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.APP_DATE.getValue()}" value="<fmt:formatDate value='${applicationDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<fmt:parseDate value="${timecardApplication.time}" pattern="HH:mm" var="time" type="date" />
<label for="${AttributeConst.APP_TIME.getValue()}">時間</label><br />
<input type="time" name="${AttributeConst.APP_TIME.getValue()}" value="<fmt:formatDate value='${time}' pattern='HH:mm' />" />
<br /><br />

<label for="${AttributeConst.APP_CONTENT.getValue()}">申請理由</label><br />
<textarea name="${AttributeConst.APP_CONTENT.getValue()}" rows="10" cols="50">${timecardApplication.appContent}</textarea>
<br /><br />

<input type="hidden" name="${AttributeConst.APPROVE_FALSE.getIntegerValue()}" value="${timecardApplication.appApprove}" />
<input type="hidden" name="${AttributeConst.APP_ID.getValue()}" value="${timecardApplication.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">申請</button>

        </form>
        <p><a href="<c:url value='?action=${actATT}&command=${commIdx}' />">一覧に戻る</a></p>
    </c:param>
</c:import>
