# Why Spring Security?
	 -> TO protect data, business logic, Algorithams etc
	 -> It is a non-functiona requirement 
	 -> Need to focus from development phase onwords
	 -> Types: SSL, Firewalls, Https, Authenticaton, Authrsation
	 -> if no security - Vulnerabilities, data breach, brand , trust issue
	 -> CSRF, broken authentication

# Add Spring Security Dependency
	-> SpSec defualtly provides a login mechanisam
		username as user
		password will be printed on console(every time new)
		
		we can configure our own credentials in .properties file
		https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html
		
		spring.security.user.name  default :user
		spring.security.user.password
		*once you entered the crednetials, it will not ask for subsequent requests
		
		Servlets/FIlters -> 
			Browsers->Http Protoca -> Servelt Container ->Http Protocal->Httpr requests
			Filters intecepts the each incoming requests and performs some logic
	#Internal FLow:
		1. User provides credentials
		
		2. Spring Security Filters, 
				monitor  each and every request , path the user requested, based on the
			configuration determines wether it is protected/public or not. Based on this wehere the authentication needs to be enforced
			or not, if yes prompts the login screen.
			Also checks the wether the use already login , if so it levarages the existing token.session etc
			if not prompts the login screen			
			there are around 15-20 security filters are available
		
		3.  Authetication, 
				Extracsts the User/passwords and created the authenication object(core standards).
			ONce Authetication object created it hand over to Auth Mgr.	
		
		4. 	Auth Mgr, 
				manager calss.interface which manages the actual auth logic.
			it checks the what all the Auth Providers are in my application.
			DB/LDAP/Auth Server/Cache/OAuth2 ( multiple Auth Prviders)
			Mgr passes the control to all providers one by one till it reaches the success
			
		5. Auth Providers,
				Actual place to providet the auth logic. Or we can levarage the spring provided 
			auth proviers like UserDetailsManager/srvices etc
		
		6. Password Encoder, passwords related standard/logics 
		 
		7. Auth Providers+ password ecoders works togeteher and validates the user and handovers the requests to AutMgr
		
		8. Auth Mgr--> FIlters
		
		9. If auth success , stores the details in context/session
		
		10. Response will be send to User
		
		Authorsation Filter --> restricts the url
			call the AuthMaager.check to check the usr is public or protect
			
		DefaultLoginPageGeneraor --> generates the loginpage
		UserNamePasswordAuthFileter --> to extract the userrname/passwords , 
		
			
		For the first time , JSESSIONID will be generated and stored as cookie,
			as long as the same cokkie available it will not ask the credentials again
			
	#Eazy Bank application
		*Services without Security
			-> contact
			-> noices
		*Services with Security
			-> myAccount
			-> myBalance
			-> myLoans
			-> myCards
	* By Default SpSec protects all the paths present in the web application-properties
	
		SpringBootWebSecurityConfiguration.java
		# Default configuration
		@Bean
		@Order(SecurityProperties.BASIC_AUTH_ORDER)
		SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests().anyRequest().authenticated();
			http.formLogin();
			http.httpBasic();
			return http.build();
		}
		
	* If user specifies his own SecurityFilterChain bean , then the default configuration will back off
	
	#Custom configuration
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.autheticateHttpRequests()
		.requestMatchers("/myAccount", "/myBalance/**").autheticated()
		.requestMatchers("/contact").permitAll()
		.and().formLogin().and().httpBasic;
		
		return http.build();
		}
		
	#denyAll()
		-> First it authenticates and then auhorises(deny all)
		http.autheticateHttpRequests().anyRequest().denyAll();
	#permitAll()
		-> permits all without authenication
		http.autheticateHttpRequests().anyRequest().denyAll();
		
	* So far we configure only one credentials in application properties
		Solution : store in InMemory(not recommended0 
				   store in DB
	
	#InMemoryUserDetailsManager
	Approach1:
	 @Bean
	 public InMemoryUserDetailsManager userDetailaManager() {
		UserDetails admin = User.withDefaultPasswordEncoder()
								.username("admin")
								.password("admin123")
								.authorities("admin)
								.build();
								
		return new InMemoryUserDetailsManager(admin, ...);
	 }
		
		
	Approach2:
	@Bean
	public InMemoryUserDetailsMaanger userDetailsManager() {
		UerDetails user = User.withUserName("admin")
							  .password("admin123)
							  .authorities("admin)
							  .build();
		return new InMemoryUserDetailsMaanger(admin, ...);
		
	}
		
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
		
	UserDetailsService.java(interface)  loadByUserName();
		-> UserDetailsManager.java (interface) createUser(), deleteUser(), updateUser(), changePassword() etc
				-> InMemoryUserDetailsMaanger, JdbcUserDetialsManager , LdapUserDetailsManager classes
	UserDetails interface : Represent the detaiils of end user (username, passowird, auhorities)
				->getAuthorities(), getPassword(), getUserName(), isAccountNonExpired(), isAccountNonLocked(), isEnabled(), isCredentialNonExpired()
	
	FreeMySQlHosting.com
	SQlEctron
	
	#JdbcUserDetialsManager
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetialsManager(dataSource);
	}
	spring.datasource.url=jdbc:postgresql://localhost:5432//springSecurity
	spring.datasource.username=sudhir
	spring.datasource.password=
	spring.datasource.driver-class-name=
		
		
		