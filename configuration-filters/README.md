## 04. Spring Security Configuration & Filter Order

#### Configuration Example 01 - Custom SecurityBuilder
1. Test
   - configuration: config.app.SecurityConfigEx01
   - test: config.app.SecurityConfigEx01Test
2. formLogin()
   - FormLoginConfigurer
3. filters order
   <pre>
   01. ChannelProcessingFilter 
   02. DisableEncodeUrlFilter                  (default)  1
   03. WebAsyncManagerIntegrationFilter        (default)  2
   04. SecurityContextHolderFilter             (default)  3 
   05. ConcurrentSessionFilter
   06. HeaderWriterFilter                      (default)  4
   07. CsrfFilter                              (default)  5
   08. LogoutFilter                            (default)  6
   09. UsernamePasswordAuthenticationFilter               7  *  
   10. DefaultResourcesFilter                             8  *
   11. DefaultLoginPageGeneratingFilter                   9  *
   12. DefaultLogoutPageGeneratingFilter                  10 *
   13. BasicAuthenticationFilter               
   14. RequestCacheAwareFilter                 (default)  11
   15. SecurityContextHolderAwareRequestFilter (default)  12
   16. JaasApiIntegrationFilter
   17. RememberMeAuthenticationFilter
   18. AnonymousAuthenticationFilter           (default)  13
   19. SessionManagementFilter                            
   20. ExceptionTranslationFilter              (default)  14
   21. AuthorizationFilter
   </pre>



#### Configuration Example 02 - Custom SecurityBuilder
1. Test
   - configuration: config.app.SecurityConfigEx02
   - test: config.app.SecurityConfigEx02Test
2. formLogin()
   - FormLoginConfigurer
3. httpBasic()
   - HttpBasicConfigurer
   - configure HTTP Basic authentication
4. filter order
   <pre>
   01. ChannelProcessingFilter 
   02. DisableEncodeUrlFilter                  (default)  1
   03. WebAsyncManagerIntegrationFilter        (default)  2
   04. SecurityContextHolderFilter             (default)  3 
   05. ConcurrentSessionFilter
   06. HeaderWriterFilter                      (default)  4
   07. CsrfFilter                              (default)  5
   08. LogoutFilter                            (default)  6
   09. UsernamePasswordAuthenticationFilter               7  
   10. DefaultResourcesFilter                             8
   11. DefaultLoginPageGeneratingFilter                   9
   12. DefaultLogoutPageGeneratingFilter                  10
   13. BasicAuthenticationFilter                          11 *               
   14. RequestCacheAwareFilter                 (default)  12
   15. SecurityContextHolderAwareRequestFilter (default)  13
   16. JaasApiIntegrationFilter
   17. RememberMeAuthenticationFilter
   18. AnonymousAuthenticationFilter           (default)  14
   19. SessionManagementFilter                            
   20. ExceptionTranslationFilter              (default)  15
   21. AuthorizationFilter
   </pre>



#### Configuration Example 03 - Custom SecurityBuilder
1. Test
   - configuration: config.app.SecurityConfigEx03
   - test: config.app.SecurityConfigEx03Test
2. formLogin()
   - FormLoginConfigurer
3. httpBasic()
   - HttpBasicConfigurer
   - configure HTTP Basic authentication
4. authorizeRequests() \[Deprecate\]
   - Access Control
   - Authorization Configurer
5. filters order
   <pre>
   01. ChannelProcessingFilter 
   02. DisableEncodeUrlFilter                  (default)  1
   03. WebAsyncManagerIntegrationFilter        (default)  2
   04. SecurityContextHolderFilter             (default)  3 
   05. ConcurrentSessionFilter
   06. HeaderWriterFilter                      (default)  4
   07. CsrfFilter                              (default)  5
   08. LogoutFilter                            (default)  6
   09. UsernamePasswordAuthenticationFilter               7  
   10. DefaultResourcesFilter                             8
   11. DefaultLoginPageGeneratingFilter                   9
   12. DefaultLogoutPageGeneratingFilter                  10
   13. BasicAuthenticationFilter                          11              
   14. RequestCacheAwareFilter                 (default)  12
   15. SecurityContextHolderAwareRequestFilter (default)  13
   16. JaasApiIntegrationFilter
   17. RememberMeAuthenticationFilter
   18. AnonymousAuthenticationFilter           (default)  14
   19. SessionManagementFilter                            
   20. ExceptionTranslationFilter              (default)  15
   21. AuthorizationFilter                                16 *
   </pre>



#### Configuration Example 04 - Custom SecurityBuilder
1. Test
   - configuration: config.app.SecurityConfigEx04
   - test: config.app.SecurityConfigEx04Test
2. formLogin()
   - FormLoginConfigurer
3. authorizeHttpRequests()
   - Access Control
   - Authorization Configurer
4. filter order
   <pre>
   01. ChannelProcessingFilter 
   02. DisableEncodeUrlFilter                  (default)  1
   03. WebAsyncManagerIntegrationFilter        (default)  2
   04. SecurityContextHolderFilter             (default)  3 
   05. ConcurrentSessionFilter
   06. HeaderWriterFilter                      (default)  4
   07. CsrfFilter                              (default)  5
   08. LogoutFilter                            (default)  6
   09. UsernamePasswordAuthenticationFilter               7  
   10. DefaultResourcesFilter                             8
   11. DefaultLoginPageGeneratingFilter                   9
   12. DefaultLogoutPageGeneratingFilter                  10
   13. BasicAuthenticationFilter             
   14. RequestCacheAwareFilter                 (default)  11
   15. SecurityContextHolderAwareRequestFilter (default)  12
   16. JaasApiIntegrationFilter
   17. RememberMeAuthenticationFilter
   18. AnonymousAuthenticationFilter           (default)  13
   19. SessionManagementFilter                            
   20. ExceptionTranslationFilter              (default)  14
   21. AuthorizationFilter                                15
   </pre>


#### Configuration Example 05 - Custom SecurityBuilder
1. Test
   - configuration: config.app.SecurityConfigEx05
   - test: config.app.SecurityConfigEx05Test
2. formLogin()
   - FormLoginConfigurer
3. authorizeHttpRequests()
   - Access Control
   - Authorization Configurer
4. filter order
   <pre>
   01. ChannelProcessingFilter 
   02. DisableEncodeUrlFilter                  (default)  1
   03. WebAsyncManagerIntegrationFilter        (default)  2
   04. SecurityContextHolderFilter             (default)  3 
   05. ConcurrentSessionFilter
   06. HeaderWriterFilter                      (default)  4
   07. CsrfFilter                              (default)  5
   08. LogoutFilter                            (default)  6
   09. UsernamePasswordAuthenticationFilter               7  
   10. DefaultResourcesFilter                             
   11. DefaultLoginPageGeneratingFilter
   12. DefaultLogoutPageGeneratingFilter
   13. BasicAuthenticationFilter             
   14. RequestCacheAwareFilter                 (default)  8
   15. SecurityContextHolderAwareRequestFilter (default)  9
   16. JaasApiIntegrationFilter
   17. RememberMeAuthenticationFilter
   18. AnonymousAuthenticationFilter           (default)  10
   19. SessionManagementFilter                            
   20. ExceptionTranslationFilter              (default)  11
   21. AuthorizationFilter                                12
   </pre>


#### Practical Example: mysite05
