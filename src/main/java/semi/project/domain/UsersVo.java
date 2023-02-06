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
	
	private String user_id;
	private String password; // 비밀번호
	private String user_name;	// 이름
	private String user_type;
	private boolean use_yn ; // 사용여부
	private String user_email; // 학생 메일
	private String user_tel; // 학생 전화번호
	private String sl_yn; // 승인 여부
	private String user_authority; // 권한
	private String first_input_ilsi; // 가입날짜
	
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
		return user_id;
	}
}
