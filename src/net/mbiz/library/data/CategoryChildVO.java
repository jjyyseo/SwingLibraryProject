package net.mbiz.library.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryChildVO {
	
	private int CCtgIdx;          /*idx*/
	private int CCtgPnt;          /*부모 카테고리 idx*/
	private String CCtgNm;        /*자식 카테고리 명*/
	private Date CCtgReg;         /*카테고리 등록 일자*/
	private Date CCtgMdf;         /*카테고리 수정 일자*/
}
