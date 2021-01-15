package ni.org.ics.smil.cssfv.api.security;

public class JwtSecurityConstants {
	public static final String SECRET = "p3s6v9y$B&E)H@McQfThWmZq4t7w!z%C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeSh";
	public static final long EXPIRATION_TIME = 900_000; // 15 mins
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/seguridad/usuarios/add";
	public static final String LOGIN_URL = "/authenticate";
	public static final String EXCEPTION_ATR = "exception"; 
}
