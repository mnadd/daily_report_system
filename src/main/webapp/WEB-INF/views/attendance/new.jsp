<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="action" value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commWorkfin" value="${ForwardConst.CMD_WORKFIN.getValue()}" />



<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>打刻ページ</h2>
            <c:if test="${errors != null}">
                <div id="flush_error">
                    入力エラーがあります。<br />
                    <c:forEach var="error" items="${errors}">
                        ・<c:out value="${error}" /><br />
                    </c:forEach>
                </div>
            </c:if>

            <form method="POST" action="<c:url value='?action=${action}&command=${commCrt}' />">
                <button type="submit" name="${AttributeConst.ATT_START.getValue()}" value="start" >出勤</button>
                <input type="hidden" name="${AttributeConst.ATT_ID.getValue()}" value="${attendance.id}" />
                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            </form>

            <form method="POST" action="<c:url value='?action=${action}&command=${commWorkfin}' />">
                <button type="submit" name="${AttributeConst.ATT_FINISH.getValue()}" value="finish" >退勤</button>
                <input type="hidden" name="${AttributeConst.ATT_DATE.getValue()}" value="${attendance.attendanceDate}" />
                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            </form>

        <p><a href="<c:url value='?action=${action}&command=${commIdx}' />">一覧に戻る</a></p>
    </c:param>
</c:import>