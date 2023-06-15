package ch.bbw.pr.sospri.member;

import org.jboss.aerogear.security.otp.api.Base32;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * A member
 *
 * @author Peter Rutschmann
 * @version 09.04.2020
 */
@Entity
@Table(name = "member")
public class Member {
	@Id
	@GeneratedValue(generator = "generatorMember", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "generatorMember", initialValue=20)
	private Long id;

	@NotEmpty (message = "prename may not be empty" )
	@Size(min=2, max=25, message="Die Länge des Vornamen muss 2 bis 25 Zeichen sein.")
	private String prename;

	@NotEmpty (message = "lastname may not be empty" )
	@Size(min=2, max=25, message="Die Länge des Nachnamen 2 bis 25 Zeichen sein.")
	private String lastname;

	@NotEmpty (message = "password may not be empty" )
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
	private String password;

	@NotEmpty (message = "username may not be empty" )
	private String username;

	private String authority;

	private LocalDateTime date;


	private boolean uses2FA;
	private String secret;

	public boolean isUses2FA() {
		return uses2FA;
	}

	public void setUses2FA(boolean uses2FA) {
		this.uses2FA = uses2FA;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", prename=" + prename + ", lastname=" + lastname + ", password=" + password
				+ ", username=" + username + ", authority=" + authority + "]";
	}
}