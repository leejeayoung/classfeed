package semi.project.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeVo { // 주제
	private String th_code; // 주제 코드
	private String user_id; // 선생님 아이디
	private String su_code; // 과목 코드
	private String th_name; // 주제 이름
	private String first_input_ilsi;
}
