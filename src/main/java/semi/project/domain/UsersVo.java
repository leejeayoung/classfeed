package semi.project.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersVo implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private String userId; // 아이디
	private String password; // 비밀번호
	private String userName; // 이름
	private boolean useYn ; // 사용여부
	private String userEmail; // 학생 메일
	private String userTel; // 학생 전화번호
	private int slYn; // 승인 여부
	private String userAuthority; // 권한
	private String firstInputIlsi; // 가입날짜
	
	private List<GrantedAuthority> authorities;
	
	public void setAuthorities(List<String> authList) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (int i = 0; i < authList.size(); i++) {
			authorities.add(new SimpleGrantedAuthority(authList.get(i)));
		}

		this.authorities = authorities;
	}
	
	@Override
	// PW
	public String getPassword() {

		return password;
	}
	
	@Override
	// 권한
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	// 계정이 만료 되지 않았는가?
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	// 계정이 잠기지 않았는가?
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	// 패스워드가 만료되지 않았는가?
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	// 계정이 활성화 되었는가?
	public boolean isEnabled() {

		return true;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userId;
	}
}
