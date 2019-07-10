package ru.amaev.budget.configs

//@Component
//class CustomAuthenticationProvider : AuthenticationProvider {
//
//    val logger: Logger = LoggerFactory.getLogger(this::class.java)
//    @Autowired
//    lateinit var userRepository: UserRepository
//
//
//    override fun authenticate(authentication: Authentication): Authentication {
//        val name = authentication.name
//        val password = authentication.credentials.toString()
//
//        val exitedUser = userRepository.findByLogin(name)
//
//        if (exitedUser == null) {
//            logger.error("Authentication failed for user = $name")
//            throw BadCredentialsException("Authentication failed for user = $name")
//        }
//
//        // find out the exited users
//        val grantedAuthorities = mutableListOf<GrantedAuthority>()
//        grantedAuthorities.add(SimpleGrantedAuthority(exitedUser.role.toString()))
//        val auth = UsernamePasswordAuthenticationToken(name, password, grantedAuthorities)
//
//        logger.info("Succesful Authentication with user = $name!")
//        return auth
//    }
//
//    override fun supports(authentication: Class<*>): Boolean {
//        return authentication == UsernamePasswordAuthenticationToken::class.java
//    }
//}