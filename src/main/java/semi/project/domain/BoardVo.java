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
public class BoardVo {
	
	private long board_seq; 
	private String user_id; 
	private String theme_cd;
	private String board_title; 
	private String board_content; 
	private String first_input_ilsi; 
	private String board_dead_line; 
	private String board_ofname; 
	private String board_bfname; 
	private String board_fsize; 
	private String su_code;

}
