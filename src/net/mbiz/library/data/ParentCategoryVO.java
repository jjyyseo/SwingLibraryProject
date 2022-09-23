package net.mbiz.library.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ParentCategoryVO {
	
	private int pCtgIdx;          /*idx*/
	private String pCtgNm;        /*부모 카테고리 명*/
	private Date pCtgReg;         /*카테고리 등록 일자*/
	private Date pCtgMdf;         /*카테고리 수정 일자*/

	@Override
	public String toString() {
		return pCtgNm;
	}
}
