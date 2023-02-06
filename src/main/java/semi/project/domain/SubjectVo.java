package semi.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectVo { // 과목
	private String su_code; // 과목코드
	private String user_id; // 선생님 아이디
	private String user_name;
	private String su_name; // 과목명
	private String ssub_name; // 부제목
	private String keep_yn; // 보관 y/n
}
