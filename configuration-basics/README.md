## 03. Spring Security Configuration Basics



#### SecurityBuilder & SecurityConfigurer
1. spring-security-config Dependency 필요

2. @EnableWebSecurity 사용

3. WebSecuriy와 HttpSecurity를 사용한 FilterChainProxy Bean 생성

4. 1 또는 2개의 SecurityFilterChain이 생성된다.

5. HttpSecurity를 사용한 SecurityFilterChain 생성
    - 실제적인 액세스 제어 설정
    - 이 설정으로 생성되는 SecurityFilterChain에 Spring Security Filter들이 생성된다.

6. WebSecurityCustomizer
    - WebSecurty 설정을 통해 전역 접근이 가능한 설정을 한다.
    - HttpSecurity 설정으로 생성된 SecurityFilterChain에 적용되지 않는 URI를 설정한다.



#### Spring Security Filters
1. ChannelProcessingFilter

2. DisableEncodeUrlFilter
    - Session Id가 URL에 포함되는 것을 막기 위해 HttpServletResponse를 사용해서 URL이 인코딩 되는 것을 막는 필터
   
3. SecurityContextHolderFilter
    - SecurityContext가 없으면 만들어주는 필터
    - SecurityContext는 Authentication 객체를 보관하는 인터페이스 SecurityContext를 통해 한 요청에 대해서 어떤 필터에서도 같은 Authentication 객체를 사용
   
4. WebAsyncManagerIntegrationFilter
    - SpringSecurityContextHolder는 기본적으로 ThreadLocal 기반으로 동작하는데, 비동기와 관련된 기능을 쓸 때 SecurityContext를 사용할 수 있도록 만들어주는 필터
   
5. ConcurrentSessionFilter

6. HeaderWriterFilter
    - 응답에 Security와 관련된 헤더 값을 설정해주는 필터
   
7. CsrfFilter
    - CSRF 공격을 방어하기 위한 설정을 하는 필터
   
8. LogoutFilter
    - 로그아웃 요청을 처리하는 필터
   
9. UsernamePasswordAuthenticationFilter
    - username과 password를 쓰는 form 기반 인증을 처리하는 필터
    - AuthenticationManager를 통한 인증을 실행
    - 성공하면 Authentication 객체를 SecurityHolder에 저장한 후AuthenticationSuccessHandler를 실행 실패하면 AuthenticationFailureHandler를 실행

10. DefaultResourcesFilter

11. DefaultLoginPageGeneratingFilter
    - 로그인 기본 페이지를 생성하는 필터

12. DefaultLogoutPageGeneratingFilter
    - 로그아웃 기본 페이지를 생성하는 필터

13. BasicAuthenticationFilter
    - HTTP header에 인증 값을 담아 보내는 BASIC 인증을 처리하는 필터 

14. RequestCacheAwareFilter
    - 인증 처리 후 원래의 Request 정보로 재구성하는 필터

15. SecurityContextHolderAwareRequestFilter
    - 서블릿 API 보안 메서드를 구현하는 요청 래퍼로 서블릿 요청을 채우는 필터

16. JaasApiIntegrationFilter

17. RememberMeAuthenticationFilter

18. AnonymousAuthenticationFilter
    - 이 필터에 올 때까지 사용자가 인증되지 않았다면, 이 요청은 익명의 사용자가 보낸 것으로 판단할 수 있다.
    - 이 익명 사용자에 관한 처리를 하는 필터

19. SessionManagementFilter
    - 세션 생성 전략을 설정하는 필터
    - 최대 동시 접속 세션을 설정
    - 유효하지 않은 세션으로 접근했을 때의 처리
    - 세션 변조 공격 방지 등의 처리

20. ExceptionTranslationFilter
    - 필터 처리 과정에서 인증 예외 또는 인가 예외가 발생한 경우 해당 예외를 잡아서 처리하는 필터

21. AuthorizationFilter
    - 인가를 결정하는 AccessDicisionManager에게 접근 권한이 있는지 확인하고 처리하는 필터
    - 앞 필터들을 통과할 때 인증 또는 인가에 문제가 있으면 ExceptionTranslationFilter로 예외를 던진다.


#### Spring Security Configuration Example 00: FilterChainProxy Bean 직접 설정 예시
1. Configuration: config.web.SecurityConfigEx00
2. [참고용] Bean 직접 설정은 복잡하기 때문에 실무에서 거의 사용하지 않는다.


#### Spring Security Configuration Example 01: XML, SecurityBuilder 사용
1. 자동(Auto) 설정
2. Configuration: config/app/SecurityConfigEx01.xml
3. Test: config.app.SecurityConfigEx01Test
4. Filter Order
   <pre>
   01. ChannelProcessingFilter 
   02. DisableEncodeUrlFilter                  (default)  1
   03. SecurityContextHolderFilter             (default)  2
   04. WebAsyncManagerIntegrationFilter        (default)  3
   05. ConcurrentSessionFilter
   06. HeaderWriterFilter                      (default)  4
   07. CsrfFilter                              (default)  5
   08. LogoutFilter                            (default)  6
   09. UsernamePasswordAuthenticationFilter    (default)  7
   10. DefaultResourcesFilter                  (default)  8
   11. DefaultLoginPageGeneratingFilter        (default)  9
   12. DefaultLogoutPageGeneratingFilter       (default)  10
   13. BasicAuthenticationFilter               (default)  11
   14. RequestCacheAwareFilter                 (default)  12
   15. SecurityContextHolderAwareRequestFilter (default)  13
   16. JaasApiIntegrationFilter
   17. RememberMeAuthenticationFilter
   18. AnonymousAuthenticationFilter           (default)  14
   29. SessionManagementFilter                            
   20. ExceptionTranslationFilter              (default)  15
   21. AuthorizationFilter                     (default)  16
   </pre>



#### Spring Security Configuration Example 02: Java, SecurityBuilder 사용
1. 기본(Basic) 설정
2. Configuration: config.app.SecurityConfigEx02
3. Test: config.app.SecurityConfigEx02Test
4. Filter Order
   <pre>
   01. ChannelProcessingFilter 
   02. DisableEncodeUrlFilter                  (default)  1
   03. WebAsyncManagerIntegrationFilter        (default)  2
   04. SecurityContextHolderFilter             (default)  3 
   05. ConcurrentSessionFilter
   06. HeaderWriterFilter                      (default)  4
   07. CsrfFilter                              (default)  5
   08. LogoutFilter                            (default)  6
   09. UsernamePasswordAuthenticationFilter    
   10. DefaultResourcesFilter                  
   11. DefaultLoginPageGeneratingFilter        
   12. DefaultLogoutPageGeneratingFilter       
   13. BasicAuthenticationFilter               
   14. RequestCacheAwareFilter                 (default)  7
   15. SecurityContextHolderAwareRequestFilter (default)  8
   16. JaasApiIntegrationFilter
   17. RememberMeAuthenticationFilter
   18. AnonymousAuthenticationFilter           (default)  9
   19. SessionManagementFilter                            
   20. ExceptionTranslationFilter              (default)  10
   21. AuthorizationFilter
   </pre>