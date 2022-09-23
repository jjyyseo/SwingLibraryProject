package net.mbiz.library.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ChildCategoryVO {
	
	private int cCtgIdx;          /*idx*/
	private int cCtgPnt;          /*부모 카테고리 idx*/
	private String cCtgNm;        /*자식 카테고리 명*/
	private Date cCtgReg;         /*카테고리 등록 일자*/
	private Date cCtgMdf;         /*카테고리 수정 일자*/
	
	@Override
	public String toString() {
		return cCtgNm;
	}
}
