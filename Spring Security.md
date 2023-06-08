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
		UserNamePasswordAuthFileter --> 
		
			
			
		
			
			
		
		
		
		