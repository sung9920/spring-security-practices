## 05. Spring Security: Application-Level Security (De facto) Standards for Jakarta EE Development

Spring은 Jakarta EE 개발의 사실상 표준 프레임워크이다. Spring의 주요 프로젝트 중 하나인 Spring Security도 Jakarta EE 개발에서 보안을 다루는 사실상 표준 "애플리케이션 레벨"의 보안 프레임워크라 할 수 있다.

소프트웨어 시스템의 보안을 다룰 때는 소프트웨어 시스템을 여러 계층(Layer)으로 나누고 계층별 세분화 된 보안 주제를 다루는 것이 보통이다. 예를 들면, 소프트웨어 시스템을 인프라 계층과 시스템 계층으로 나누고 인프라 계층에서는 네트워크, 컴퓨팅, 운영체체 등의 레벨로 다시 세분화 하여 각 레벨에 맞는 보안 주제를 다룬다. 마찬가지로 시스템 계층도 애플리케이션, 데이터 저장(데이터베이스) 등으로 나누고 각 레벨의 보안 주제를 다룬다.
Spring Security는 소프트웨어 시스템 보안에서 애플리케이션 레벨의 보안에만 집중한다. 애플리케이션 레벨에서 다루게 되는 보안 주제는 인증(Authentication)과 권한(Authorization)이다. Spring Security는 이 주제의 완벽한 구현을 지원한다. 여기에 한 가지 더, 대부분의 Java EE 애플리케이션들이 웹을 기반하기 때문에 일반적인 웹 공격(OWASP TOP 10, https://www.owasp.org) 에 대한 대응도 완벽히 지원한다.

Spring 기반의 애플리케이션들은 실행, 실행환경 그리고 데이터 처리와 저장에 대한 대부분의 로직들이 Spring Context 내의 다수의 Bean들로 구현된다. 따라서 Spring Security가 보호해야 할 대상은 Spring Context이고 더 구체적으로는 Spring Context가 관리하는 Bean의 실행이다.
Spring과 Spring MVC 기반의 웹애플리케이션에서는 모든 HTTP 요청을 Spring Context 내의 특정 Bean(Controller)의 실행에 매핑하게 된다. 그런데, HTTP 요청에 대한 최초 실행은 Servlet Context(WAS)내의 DispatcherServlet 이기 때문에 DispatcherServlet은 요청을 Spring Context의 Bean의 실행에 전달해야 한다. 간단하게 요약했지만 이것이 Spring MVC의 핵심이다.
어떤 대상을 보호할 때는 어떤 대상의 밖에서 보호하는 것이 지극히 당연한 것이다. Spring Security는 Spring Context의 Bean에 대한 보호를 위해 Servlet Context 내의 DispatcherServlet이 처리하는 모든 요청을 DispatcherServlet 보다 먼저 감시하고 보안 정책을 적용한다. 이를 가능하게 하는 기술이 Servlet Filter 이다.

그런데 왜 Servlet Security가 아니고 Spring Security 인가?

Jakarta EE 응용 패턴인 Intercepting Filter Pattern이 적용된 Servlet Filter는 이름에도 있지만 Servlet과 함께 Servlet Context에서 관리하는 컴포넌트다. 그리고 보안이 그리 간단치만은 않는 주제이기 때문에 다수의 보안 필터들이 최종의 Servlet 실행 전까지 순차적으로 각각의 설정된 보안 정책을 적용하게 된다. 이를 Filter Chain이라 한다.
Spring Security Architecture는 전적으로 Filter와 Filter Chain을 기반하고 있다. 그런데 이 Filter와 Filter Chain의 생성/실행 Context가 Servlet Context가 아닌 Spring Context가 된다. 어떻게 Filter와 Filter Chain을 Spring Bean으로 생성/등록하고 등록된 Filter Chain 안의 Filter를 어떻게 DispatcherServlet 실행 전에 작동하게 할까? 
이 모든 질문에 대한 답은 아래 순서대로 차근! 차근!

### 00. Servlet Filter & Filter Chain
### 01. (Delegating)Filter Proxy [filter-proxy]
### 02. Filter Chain(Proxy) [filter-chain]
### 03. Spring Security Configuration Basics [configuration-basics]
### 04. Spring Security Configuration & Filter Order [configuration-filters]
