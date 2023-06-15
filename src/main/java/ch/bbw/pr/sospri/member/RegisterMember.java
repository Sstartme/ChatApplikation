package ch.bbw.pr.sospri.member;
/**
 * To regist a new Member
 * @author peter.rutschmann
 * @version 27.04.2020
 */
public class RegisterMember {
	private String prename;
	private String lastname;
	private String password;
	private String confirmation;
	private String message;


	private boolean uses2FA;

	public boolean isUses2FA() {
		return uses2FA;
	}

	public void setUses2FA(boolean uses2FA) {
		this.uses2FA = uses2FA;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	@Override
	public String toString() {
		return "RegisterMember [prename=" + prename + ", lastname=" + lastname + ", password=" + password
				+ ", confirmation=" + confirmation + "]";
	}
}
