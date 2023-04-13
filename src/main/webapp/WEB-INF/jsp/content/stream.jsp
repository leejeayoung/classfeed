<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../content/header.jsp" flush="true">
	<jsp:param name="title" value="스트림"/>
</jsp:include>

<div class="contentWrap">
    <div class="innerWrap">
        <div class="conTabWarp">
            <a href="/list/mystream.do?su_code=${su_code}&&user_type=${user_type}" class="conTab on codeTransColor_color codeTransColor_border" data-sucode="${su_code}">스트림</a>
            <a href="/list/myclass.do?su_code=${su_code}&&user_type=${user_type}" class="conTab">수업</a>
        </div>

        <div class="titleBox codeTransColor_back" data-sucode="${su_code}">
            <c:forEach items="${subList}" var="subjectVo">
                <p class="title">${subjectVo.su_name}</p>
                <p class="school">${subjectVo.ssub_name}</p>
                <p class="teacher">${subjectVo.user_name} 선생님</p>
            </c:forEach>
        </div>
        <div class="streamConBox">
            <div class="homeWorkBox">
                <p class="title">오늘 마감되는 과제</p>
                <c:choose>
                    <c:when test="${!empty boardList}">
                        <c:set var="now" value="<%=new java.util.Date()%>" />
                        <c:set var="nowDate"><fmt:formatDate value="${now}" pattern="yyyyMMdd"/></c:set>
                        
                        <c:forEach items="${boardList}" var="boardVo">
                            <c:set var="bdeadlineDate"><fmt:formatDate value="${boardVo.bdeadline}" pattern="yyyyMMdd"/></c:set>
                            <c:if test="${nowDate == bdeadlineDate}">
                                <c:set var="cnt" value="1"/>
                                <a href="../myboard/content.do?bseq=${boardVo.bseq}" class="nowDateLink">
                                    <b>${boardVo.btitle}</b>
                                    <span>${boardVo.bdeadline} 까지</span>
                                </a>
                            </c:if>
                            <c:if test="${empty cnt}">
                                <p class="noWork">오늘 마감되는 과제가 없습니다.</p>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:when test="${empty boardList}">
                        <p class="noWork">오늘 마감되는 과제가 없습니다.</p>
                    </c:when>
                </c:choose>
            </div>

            <div class="streamCardWrap">
                <div class="streamCard noticeCard">
                    <div class="iconCircle">
                        <i class="fas fa-bullhorn"></i>
                    </div>
                    <p class="title">학생들에게 공지할 내용을 입력하세요.</p>

                    <div class="textAreaWrap">
                        <form action="/list/notice.do" method="post">
                        <textarea name="ncontent" placeholder="공지내용을 입력해주세요."></textarea>
                        <div class="btnWrap">
                            <button class="cancleBtn" type="reset">취소</button>
                            <button class="submitBtn codeTransColor_back" data-sucode="${su_code}" type="submit">등록</button>
                            <input type="hidden" name = "${_csrf.parameterName}" value="${_csrf.token}">
                        </div>
                        </form>
                    </div>
                    <script>

                    </script>

                </div>
                <c:choose>
                    <c:when test="${!empty noticeList}">
                        <c:forEach items="${noticeList}" var="noticeVo">
                            <div class="streamCard">
                                <div class="iconCircle codeTransColor_back" data-sucode="${su_code}">
                                    <i class="fas fa-bullhorn"></i>
                                </div>
                                <a href="javascript:void(0)" class="text" style="text-decoration: none; cursor: default;">
                                    <%-- <p class="title">${noticeVo.user_id} </p> --%>
                                    <c:if test="${noticeVo.user_type eq 'teacher' }">
                                    	<p class="title">${noticeVo.user_name} 선생님</p>
                                    </c:if>
                                    <c:if test="${noticeVo.user_type eq 'student' }">
                                    	<p class="title">${noticeVo.user_name} 학생</p>
                                    </c:if>
                                    
                                    <p class="date">${noticeVo.first_input_ilsi}</p>
                                    <pre class="content">${noticeVo.notice_content}</pre>
                                </a>
                                



                                <c:choose>
                                    <c:when test="${!empty tList}">
                                        <c:forEach items="${tList}" var="teacherVo">
                                            <c:if test="${teacherVo.user_id == noticeVo.user_id}">
                                                <div class="moreBtnWrap">
                                                    <button class="moreBtn">
                                                        <i class="fas fa-ellipsis-v"></i>
                                                    </button>
                                                    <div class="moreWrap">
                                                        <a href="noticeDel.do?nseq=${noticeVo.notice_seq}" class="moreAnchor deleteClass" data-class="" style="top:55px">삭제</a>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${!empty sList}">
                                        <c:forEach items="${sList}" var="studentVo">
                                            <c:if test="${studentVo.user_id == noticeVo.user_id}">
                                                <div class="moreBtnWrap">
                                                    <button class="moreBtn">
                                                        <i class="fas fa-ellipsis-v"></i>
                                                    </button>
                                                    <div class="moreWrap">
                                                        <a href="noticeDel.do" class="moreAnchor deleteClass" data-class="" style="top:55px">삭제</a>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>



                                
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${!empty boardList}">
                        <c:forEach items="${boardList}" var="boardVo">


                            <div class="streamCard">
                                <div class="iconCircle codeTransColor_back" data-sucode="${su_code}">
                                    <c:choose>
                                        <c:when test="${empty boardVo.bdeadline}">
                                            <i class="far fa-file-alt" aria-hidden="true"></i>
                                            <c:set var="boardType" value="자료"/>
                                        </c:when>
                                        <c:when test="${!empty boardVo.bdeadline}">
                                            <c:set var="boardType" value="과제"/>
                                            <i class="fas fa-pencil-alt"></i>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <a href="../myboard/content.do?bseq=${boardVo.bseq}" class="text">
                                    <p class="title">${boardVo.user_id} 님이 새 ${boardType}을 게시 : ${boardVo.btitle}</p>
                                    <p class="date">${boardVo.brdate}</p>
                                </a>

                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>

            </div>
        </div>

    </div>
</div>

</body>
<script>

    $(document).on('click', '.streamCard.noticeCard .title', function(){
        $('.streamCard.noticeCard').addClass('on');
        $('.textAreaWrap textarea').focus();
    })
    $(document).on('click','.textAreaWrap .cancleBtn', function(){
        $('.streamCard.noticeCard').removeClass('on')
    })

</script>
</html>
