package com.cimb.tokolapak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.tokolapak.dao.UserRepo;
import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.entity.User;
import com.cimb.tokolapak.util.EmailUtil;

@RestController
@RequestMapping("/users")
@CrossOrigin (origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserRepo userRepo;
	
	private PasswordEncoder pwEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private EmailUtil emailUtil;
	
	@PostMapping
	public User registerUser(@RequestBody User user) {
		String encodedPassword = pwEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		User savedUser = userRepo.save(user);
		savedUser.setPassword(null);
		int userId = user.getId();
		
		emailUtil.sendEmail(user.getEmail(), "Registrasi User", " <div>\r\n" + 
				"          <h1>Selamat bergabung bersama kami!</h1>\r\n" + 
				"          Klik link berikut untuk verifikasi akun\r\n" + 
				"          <a href=\"http://localhost:8080/users/verifyUser/"+userId+"\">Verifikasi sekarang</a>\r\n" + 
				"        </div>");

		return savedUser;
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) {
		User findUser = userRepo.findByUsername(user.getUsername()).get();
		
		if (pwEncoder.matches(user.getPassword(), findUser.getPassword())) {
			findUser.setPassword(null);
			return findUser;
		}
		
		return null;
	}
	
//	cara yg direcomendasikan untuk login
	@GetMapping("/login")
	public User getLoginUser(@RequestParam String username, @RequestParam String password) {
		User findUser = userRepo.findByUsername(username).get();
		
		if (pwEncoder.matches(password, findUser.getPassword())) {
			findUser.setPassword(null);
			return findUser;
		}
		
		return null;
	}
	
	@GetMapping
	public Iterable<User> getUser() {
		return userRepo.findAll();
	}
	
	@PostMapping("/sendEmail")
	public String sendEmailTesting() {
		this.emailUtil.sendEmail("bevinda.je@gmail.com", "Testing spring email", "<h1>Hallo<h1> \nCoba ngirim email pake spring");
		return "Email Sent!";
	}
	
	@GetMapping("verifyUser/{userId}")
		public String verifyUser(@PathVariable int userId) {
			User findUser = userRepo.findById(userId).get();
			findUser.setVerified(true);
			userRepo.save(findUser);
			return "Verifikasi sukses!";
		}
}
