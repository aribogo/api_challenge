package gft.api.security;

import java.util.Random;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.api.security.dto.AuthenticationDTO;
import gft.api.security.dto.TokenDTO;
import gft.api.security.dto.UserDTO;
import gft.api.security.entity.User;
import gft.api.security.service.AuthenticationService;
import gft.api.security.service.UserService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	private final AuthenticationService authenticationService;
	private final UserService userService;

	public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
		this.authenticationService = authenticationService;
		this.userService = userService;
	}

	/**
	 * Authenticates user and gives a token or gives an unauthorized exception.
	 * @param authForm
	 * @return
	 */
	@PostMapping
	public ResponseEntity<TokenDTO> authenticate(@RequestBody AuthenticationDTO authForm) {

		try {
			return ResponseEntity.ok(authenticationService.authenticate(authForm));
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}
	
	/**
	 * Creates new user with random password displayed in console. 
	 * @param newUser
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody UserDTO newUser){
		try {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String randomPass = alphaNumericString(10);
		System.out.println(randomPass);
		
			User user = new User(null, newUser.getUsername(), passwordEncoder.encode(randomPass));
			user = userService.saveUser(user);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
		
		
		return null;
	}
	
	
	/**
	 * Password builder
	 * 
	 * @param len
	 * @return
	 */
	private static String alphaNumericString(int len) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

}
