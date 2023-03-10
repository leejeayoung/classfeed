<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="수업보관함"/>
</jsp:include>

<div class="contentWrap">
    <div class="contentInnerWrap">
        <c:choose>
            <c:when test="${!empty tList}">
                <c:choose>
                    <c:when test="${!empty tSubList}">
                        <div class="classCardWrap">
                            <c:forEach items="${tSubList }" var="subjectVo">

                                <c:choose>
                                    <c:when test="${subjectVo.keep_yn == 'Y' }">
                                        <c:set var="yCnt" value="${yCnt+1}" />
                                    </c:when>
                                </c:choose>

                                <c:if test="${subjectVo.keep_yn!= 'N' }">
                                    <div class="classCard" data-sucode="${subjectVo.su_code}">
                                        <div class="cardTop codeTransColor_back" data-sucode="${subjectVo.su_code}">
                                            <div class="titleWrap">

                                                <a href="../list/mystream.do?sucode=${subjectVo.su_code}" class="title">
                                                    <p class="tit">${subjectVo.su_name}</p>
                                                    <p class="sub">${subjectVo.ssub_name}</p>
                                                </a>
                                                <button class="moreBtn">
                                                    <i class="fas fa-ellipsis-v"></i>
                                                </button>
                                                <div class="moreWrap">
                                                    <a href="/main/keepOff.do?su_code=${subjectVo.su_code }" class="moreAnchor saveClass" data-class="">복원</a>
                                                </div>

                                            </div>
                                        </div>
                                        <c:forEach items="${tList}" var="teacherVo">
                                            <div class="teacherTag">${teacherVo.user_name} 선생님</div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </c:forEach>
                            <c:if test="${empty yCnt}"> 
								<div class="noClassWrap">
									<img src="../img/noClassImg2.png" style="width: 330px;">
									<p>보관한 클래스가 없습니다.</p>
								</div>
							</c:if>
                        </div>
                    </c:when>
                    <c:when test="${empty tSubList}">
                        <div class="noClassWrap">
                            <img src="../img/noClassImg2.png" style="width: 330px;">
                            <p>보관한 클래스가 없습니다.</p>
                        </div>
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test="${!empty sList}">
                <c:choose>
                    <c:when test="${!empty sSubList}">
                        <div class="classCardWrap">
                            <c:forEach items="${sSubList }" var="subjectVo">
                                <c:choose>
                                    <c:when test="${subjectVo.keep_yn == 'Y' }">
                                        <c:set var="yCnt" value="${yCnt+1}" />
                                    </c:when>
                                </c:choose>

                                <c:if test="${subjectVo.keep_yn!= 'N' }">
                                <div class="classCard" data-sucode="${subjectVo.su_code}">
                                    <div class="cardTop codeTransColor_back" data-sucode="${subjectVo.su_code}">
                                        <div class="titleWrap">

                                            <a href="../list/mystream.do?sucode=${subjectVo.su_code}" class="title">
                                                <p class="tit">${subjectVo.su_name}</p>
                                                <p class="sub">${subjectVo.ssub_name}</p>
                                            </a>

                                        </div>
                                    </div>
                                    <div class="teacherTag">${subjectVo.user_name} 선생님</div>
                                </div>
                                </c:if>
                            </c:forEach>
                            <c:if test="${empty yCnt}"> 
								<div class="noClassWrap">
                                    <img src="../img/noClassImg2.png" style="width: 330px;">
                                    <p>보관된 클래스가 없습니다.</p>
                                </div>
							</c:if>
                        </div>
                    </c:when>
                    <c:when test="${empty sSubList}">
                        <div class="noClassWrap">
                            <img src="../img/noClassImg2.png" style="width: 330px;">
                            <p>보관된 클래스가 없습니다.</p>
                        </div>
                    </c:when>
                </c:choose>
            </c:when>
        </c:choose>

    </div>
</div>


</body>

</html>