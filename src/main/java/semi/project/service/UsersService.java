package semi.project.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import semi.project.domain.UsersVo;

public interface UsersService {
	List<?> selectUserList() throws Exception;

	UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
