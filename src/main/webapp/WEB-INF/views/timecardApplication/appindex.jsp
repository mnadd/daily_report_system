<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actAPP" value="${ForwardConst.ACT_APP.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>自分の申請　一覧</h2>
        <div>
            <button type="button" onclick="location.href='?action=${actAPP}&command=${commNew}'" >申請</button>
        </div>
        <table id="timecardapplication_list">
            <tbody>
                <tr>
                    <th class="timecardApplication_date">対象日</th>
                    <th class="timecardApplication_comment">コメント</th>
                    <th class="timecardApplication_action">操作</th>
                </tr>
                    <c:forEach var="timecardApplication" items="${timecardApplications}" varStatus="status">

                        <tr class="row${status.count % 2}">
                            <td class="timecardApplication_date"><c:out value="${timecardApplication.timecardApplicationDate}" /></td>
                            <td class="timecardApplication_comment"><c:out value="${timecardApplication.comment}" /></td>
                            <td class="timecardApplication_action">
                                <c:choose>
                                    <c:when test="${timecardApplication.appApprove == AttributeConst.APPROVE_TRUE.getIntegerValue()}">
                                        （承認済）
                                    </c:when>
                                    <c:otherwise>
                                        <a href="<c:url value='?action=${actAPP}&command=${commShow}&id=${timecardApplication.id}' />">詳細を見る</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                     </c:forEach>
                </tbody>
            </table>

            <div id="pagination">
            (全 ${appCount} 件) <br />
            <c:forEach var="i" begin="1" end="${((appCount - 1) / maxRow) + 1}" step="1">
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