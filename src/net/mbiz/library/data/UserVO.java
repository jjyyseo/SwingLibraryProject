package net.mbiz.library.data;

import lombok.Data;

@Data
public class UserVO {
	
	private String userId;	      /* 아이디*/
	private String userPw;	      /* 비밀번호*/
	private String userNm;	      /* 유저명*/
	private String enabled;	      /* 사용 여부 : y, n*/

}
