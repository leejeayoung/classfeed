package semi.project.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVo { // 공지
	
	private long notice_seq; // 공지 번호
	private String first_input_ilsi; // 작성 날짜
	private String notice_content; // 공지 내용



	private String user_id; 
	private String su_code; //해당 클래스 코드
	private String user_name;
	private String user_type;
}
