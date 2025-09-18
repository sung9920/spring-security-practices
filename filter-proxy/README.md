## 01. (Delegating)Filter Proxy [filter-proxy]


#### Proxy Design Pattern


#### DelegatingFilterProxy
1. Servlet Context에 설정되는 서블릿 필터
2. DispatcheServlet가 받는 모든 요청(request)을 DispatcheServlet 앞에서 먼저 받아 Spring Application Context의 Filter Bean를 실행하는 Proxy 역할을 한다.
3. Spring Security에서는 Spring Application Context의 Filter Bean에게 보안(인증+권한)과 관련된 처리를 위임(delegation) 한다.


#### 예제01: 웹애플리케이션: tomcat에서 실행하기
1. MvcWebApplicationInitializer: DelegatingFilterProxy 등록
2. AppConfig: RealFilter Bean 등록
3. /hello 접근하여 RealFilter Bean 작동을 확인: Cookie("RealFilter", "Works")

#### 예제02: Test Case: MockMvc 사용
1. filter.MyFilterTest