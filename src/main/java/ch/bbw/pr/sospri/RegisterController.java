package ch.bbw.pr.sospri;

import ch.bbw.pr.sospri.member.Member;
import org.jboss.aerogear.security.otp.api.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.member.MemberService;
import ch.bbw.pr.sospri.member.RegisterMember;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * RegisterController
 * @author Peter Rutschmann
 * @version 26.03.2020
 */
@Controller
public class RegisterController {
	Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	MemberService memberservice;

	@Autowired
	private ReCaptchaValidationService reCaptchaService;

	@Autowired
	CaptchaSettings captchaSettings;


	@GetMapping("/get-register")
	public String getRequestRegistMembers(Model model) {
		System.out.println(captchaSettings);
		System.out.println(captchaSettings.getSecret());
		System.out.println(captchaSettings.getSite());
		System.out.println("getRequestRegistMembers");
		model.addAttribute("registerMember", new RegisterMember());
		return "register";
	}

	public String passwordEncoder(String password) throws IOException {
		String pepper = "A3";
		int iterations = 20000;
		int hashWidth = 256;
		Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(pepper, iterations, hashWidth);
		pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
		return pbkdf2PasswordEncoder.encode(password);
	}

	@PostMapping("/get-register")
	public String postRequestRegistMembers(@Valid RegisterMember registerMember, BindingResult bindingResult,
		@RequestParam(name="g-recaptcha-response") String captcha, Model model){
			if (! reCaptchaService.validateCaptcha(captcha)){
				logger.debug("Captcha fails");
				registerMember.setMessage("Captcha was not correct!");
				logger.error("Captcha failure: Captcha was not correct");
				return "register";
			}
			logger.debug("Captcha is correctè");

		System.out.println("postRequestRegistMembers: registerMember");
		System.out.println(registerMember);

		try{
			if (memberservice.getByUserName(registerMember.getPrename().toLowerCase()+"."+registerMember.getLastname().toLowerCase()) != null) {
				System.out.println("User allready exists, choose other first- or lastname.");
				registerMember.setMessage("Username " + registerMember.getPrename().toLowerCase() + "." + registerMember.getLastname().toLowerCase() + " allready exists");
				return "register";
			}
			//ADD Memnber




			Member member = new Member();
			member.setPrename(registerMember.getPrename());
			member.setLastname(registerMember.getLastname());
			member.setPassword(registerMember.getPassword());
			member.setAuthority("member");
			LocalDateTime date = LocalDateTime.now();
			member.setDate(date);
			member.setUsername(registerMember.getPrename().toLowerCase() + "." + registerMember.getLastname().toLowerCase());
			member.setSecret(Base32.random());
			registerMember.setUses2FA(true);
			logger.info("User got registered", member);
			if (Objects.equals(member.getPassword(), registerMember.getConfirmation())){
				member.setPassword(passwordEncoder(registerMember.getPassword()));
				memberservice.add(member);
				if (registerMember.isUses2FA()) {
					logger.debug("uses 2FA");
					model.addAttribute("qr", memberservice.generateQRUrl(member));
					memberservice.add(member);
					logger.info("Member successfully registered: {}", registerMember);
					getRequestRegistered(model, member);
					return "registerconfirmed";
				}
			}else{
				System.out.println("Conformation is not equal to Password");
				return "register";
			}


		}catch (Exception ex){
			System.out.println("ERROR" + ex.getMessage() + ex.getStackTrace());
		}

		return "registerconfirmed";
	}

	@GetMapping("/registerconfirmed")
	public String getRequestRegistered(Model model, Member newMember) {
		logger.info("Registered new Member: {}", newMember);
		model.addAttribute("member", newMember);
		return "registerconfirmed";
	}
}