package net.mbiz.library.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 도서 데이터를 추가하는 클래스.
 * @author metabiz
 */
public class MakeBookList {
	
	private static MakeBookList makeBookList = new MakeBookList();
	
	private MakeBookList() {
	}
	
	public static MakeBookList getInstance() {
	   return makeBookList;
	}
	
	public List<BookVO> addBookData() {
		List<BookVO> bookList = new ArrayList<>();
		
		BookVO bv1 = new BookVO();
		bv1.setBookNo(1);
		bv1.setBookNm("흔해 빠진 이야기는 싫어!");      
		bv1.setBookWtr("칼리 다비드");                      
		bv1.setBooksub("이 이야기는 기사의 이야기란다.\r\n"
				+ "아, 싫어! 공주를 구하러 가는 기사 얘기는 흔해 빠졌어!\r\n"
				+ "너무 빤하잖아? 게다가 남녀 차별이야!\r\n"
				+ "공주들은 스스로 자기를 구할 수 있단 말이야!\r\n"
				+ "좋아, 좋아. 그럼 이 얘기는 사악한 용을\r\n"
				+ "죽이러 가는 기사 이야기로 하자….\r\n"
				+ "왜 사악한데? 용은 언제나 사악해야 해?\r\n"
				+ "그것도 너무 빤하잖아, 안 그래?");                      
		bv1.setBookIsbn(9791190704397L);                     
		bv1.setCategory("어린이");                     
		bv1.setPublisher("봄볕");                        
		
		BookVO bv2 = new BookVO();
		bv2.setBookNo(2);
		bv2.setBookNm("황금열광 : 하은경 장편소설");
		bv2.setBookWtr("하은경");
		bv2.setBooksub("『황금열광』은 금광 재벌인 김 노인이 살해된 수요일 밤의 행적을 좇으며 시작되는 추리소설로"
				+ " 십 대 소년 동재와 사건을 쫓는 강 형사가 중심 화자가 되어 이야기를 펼쳐 나간다. "
				+ "벼락부자의 헛된 꿈을 꾸던 동재가 의도치 않게 시대의 열망에 휘말려 나가는 모습이 소설 속에 흐르는 운명 교향곡을 보듯 흡인력 있는 전개로 짜여 있다. "
				+ "혼란스러운 시대 속 저마다의 열망을 품은 사람들의 모습이 과거를 뛰어넘어 현재의 청년들에게도 ‘돈의 쓰임’과 ‘선택의 무게’에 대한 메시지를 던진다.");
		bv2.setBookIsbn(9788949137018L);
		bv2.setCategory("소설");
		bv2.setPublisher("비룡소");

		BookVO bv3 = new BookVO();
		bv3.setBookNo(3);
		bv3.setBookNm("화장실 냄새를 말끔히 없애려면?");
		bv3.setBookWtr("윤효식");
		bv3.setBooksub("《용선생의 시끌벅적 과학교실 - 32 혼합물의 분리》 편에서는 우리 주변에 존재하는 "
				+ "다양한 혼합물과 이 혼합물을 분리하여 우리가 원하는 물질을 얻는 방법에 대해 알아봅니다. "
				+ "이를 위해 먼저 우리 주변 물질 중에서 혼합물을 찾아보고, 혼합물울 분리하는 다양한 방법을 단계별로 살펴봅니다. "
				+ "크기 차이나 자석을 이용하는 간단한 방법뿐 아니라 밀도 차이, 끓는점 차이, 용해도 차이, 크로마토그래피를 이용하는 방법까지 알아보지요. "
				+ "이렇게 분리해 낸 물질을 실제로 어떻게 이용하고 있는지까지 살펴보고 나면 독자 어린이들은 물질의 세계를 더욱 잘 이해할 수 있으며, "
				+ "우리가 혼합물의 분리로 얻은 물질을 다양하게 이용하면서 생활을 발전시켜왔다는 것을 과학적으로 이해하게 될 것입니다.");
		bv3.setBookIsbn(97911627322120L);
		bv3.setCategory("어린이");
		bv3.setPublisher("사회평론");
		
		BookVO bv4 = new BookVO();
		bv4.setBookNo(4);
		bv4.setBookNm("홀리데이");
		bv4.setBookWtr("T. M. 로건");
		bv4.setBooksub("T. M. 로건의 심리 스릴러 『홀리데이』가 아르테에서 출간되었다. "
				+ "T. M. 로건은 자신만의 독특한 문체로 완성한 『리얼 라이즈』로 최고의 데뷔작이라는 평가를 받으며"
				+ " 미국, 이탈리아, 폴란드, 네덜란드 등 10개국에 판권을 계약하여 전 세계의 이목을 집중시켰다. "
				+ "심리스릴러의 신예로 급부상한 그는 두 번째 작품으로 강력한 리벤지 스릴러 『29초』를 선보여 독자들의 기대에 부응하였으며 출간 즉시 베스트셀러에 올랐다. "
				+ "이로써 100만 부의 판매고를 기록한 그의 세 번째 심리스릴러 『홀리데이』는 전작을 뛰어넘은 완벽한 작품이라는 찬사를 받으며, "
				+ "한시도 눈을 뗄 수 없는 서스펜스와 예측할 수 없는 완벽한 결말로 독자들을 강렬하게 매료시킨다. 여름 휴가를 완벽하게 만들어줄 스릴러 책을 찾는다면, "
				+ "단연코 T. M. 로건의 신작 『홀리데이』일 것이다!");
		bv4.setBookIsbn(9788950901929L);
		bv4.setCategory("소설");
		bv4.setPublisher("아르테");
		
		BookVO bv5 = new BookVO();
		bv5.setBookNo(5);
		bv5.setBookNm("디디의 우산");
		bv5.setBookWtr("황정은");
		bv5.setBooksub("이제 행복해지자, 너의 행복과 더불어\r\n"
				+ "세계라는 빗속에서 황정은이 건네는 우산 같은 소설\r\n"
				+ "\r\n"
				+ "장편소설 『계속해보겠습니다』『百의 그림자』, 소설집 『파씨의 입문』 『아무도 아닌』 등으로 넓고 탄탄한 독자층을 형성한 동시에 평단의 확고한 지지를 받으며"
				+ " 명실공히 한국문학을 대표하는 작가 중 한 사람으로 자리매김한 황정은 작가의 신간 『디디의 우산』이 출간되었다. "
				+ "김유정문학상 수상작 「d」(발표 당시 제목 ‘웃는 남자’)와 『문학3』 웹 연재시 뜨거운 호응을 얻었던 「아무것도 말할 필요가 없다」, "
				+ "인물과 서사는 다르지만 시대상과 주제의식을 공유하며 서로 공명하는 연작 성격의 중편 2편을 묶은 소설집이다. "
				+ "2014년 세월호참사와 2016~17년 촛불혁명이라는 사회적 격변을 배경에 두고 개인의 일상 속에서 ‘혁명’의 새로운 의미를 탐구한 작품들이다."
				+ " 삶과 죽음, 사랑과 인간을 사유하는 깊은 성찰이 마음속 깊이 파고드는 아름다운 문장들과 어우러진 가운데 끝내 압도적인 감동을 선사하는 반가운 신작이다.");
		bv5.setBookIsbn(9788936437541L);
		bv5.setCategory("소설");
		bv5.setPublisher("창비");
		
		BookVO bv6 = new BookVO();
		bv6.setBookNo(6);
		bv6.setBookNm("우리가 빛의 속도로 갈 수 없다면");
		bv6.setBookWtr("김초엽");
		bv6.setBooksub("지난겨울까지 바이오센서를 만드는 과학도였던 김초엽 작가는, 이제 소설을 쓴다. "
				+ "「관내분실」로 한국과학문학상 중단편부문 대상을 받았다. 필명으로 낸 「우리가 빛의 속도로 갈 수 없다면」도 동시에 상을 받았다. "
				+ "‘한국 SF의 우아한 계보’라 불리며 작품 활동을 시작한 김초엽 작가는 그 후, 더욱 도약했다. "
				+ "자신만이 그려낼 수 있는 김초엽 특유의 작품세계를 보여주었다. "
				+ "투명하고 아름답지만 순진하지만은 않은, 어디에도 없는 그러나 어딘가에 있을 것 같은, 근사한 세계를 손에 잡힐 듯 이야기에 담아냈다.");
		bv6.setBookIsbn(9791190090018L);
		bv6.setCategory("소설");
		bv6.setPublisher("허블");
		
		BookVO bv7 = new BookVO();
		bv7.setBookNo(7);
		bv7.setBookNm("밝은 밤");
		bv7.setBookWtr("최은영");
		bv7.setBooksub("증조할머니에게서 나로 이어지는 여성 4대의 삶을 담은 소설. "
				+ "1930년대 황해도에서 백정의 딸로 태어나 모진 세월을 살아낸 증조할머니의 시간은 그를 닮은 나에게 전해져 새 숨을 얻고, "
				+ "나의 오늘 또한 과거와의 조우를 통해 다시 쓰인다. "
				+ "부드럽고도 힘있는 문장으로 그린 백 년의 이야기 "
				+ "-소설MD 박형욱");
		bv7.setBookIsbn(9788954681179L);
		bv7.setCategory("소설");
		bv7.setPublisher("문학동네");
		
		BookVO bv8 = new BookVO();
		bv8.setBookNo(8);
		bv8.setBookNm("이기적 유전자 The Selfish Gene");
		bv8.setBookWtr("리처드 도킨스");
		bv8.setBooksub("현대 생물학의 새로운 지평을 연 세계적인 석학 리처드 도킨스의 대표작 "
				+ "『이기적 유전자』의 40주년 기념판이 출간되었다. "
				+ "진화론의 새로운 패러다임을 제시한 이 책은 다윈의 ‘적자생존과 자연선택’이라는 개념을 유전자 단위로 끌어내려 진화를 설명한다. "
				+ "2013년 영국의 정치평론지 『프로스펙트』지가 독자들의 투표로 선정하는 "
				+ "‘세계 최고의 지성’ 1위에 오른 바 있는 도킨스는 일찍이 촉망받는 젊은 과학자로 간결한 문체와 생생한 비유, "
				+ "논리적인 전개를 갖춘 글로 능력을 인정받아 왔다. "
				+ "도킨스는 자신의 동물행동학 연구를 진화의 역사에서 유전자가 차지하는 중심적 역할에 대한 좀 더 넓은 이론적 맥락과 연결시키기 시작했는데, "
				+ "그 결과가 바로 『이기적 유전자』(초판 1976년, 개정판 1989년, 30주년 기념판 2006년, 40주년 기념판 2016년)다.");
		bv8.setBookIsbn(9788932473901L);
		bv8.setCategory("자연과학");
		bv8.setPublisher("을유문화사");
		
		BookVO bv9 = new BookVO();
		bv9.setBookNo(9);
		bv9.setBookNm("기브 앤 테이크. 주는 사람이 성공한다");
		bv9.setBookWtr("애덤 그랜트");
		bv9.setBooksub("양보하고, 배려하고, 베풀고, 희생하고, 조건 없이 주는 사람이\r\n"
				+ "어떻게 성공 사다리의 꼭대기에 올랐을까?\r\n"
				+ "\r\n"
				+ "[포천]이 선정한 세계 최고의 인맥을 쌓은 사람은 누구이며, 그 비결은 무엇인가? "
				+ "말더듬이 신참 변호사가 어떻게 재판에서 청산유수 베테랑 변호사를 이겼을까? "
				+ "미국 역사상 가장 위대한 대통령의 인사 시스템에는 어떤 비밀이 숨어 있는가? "
				+ "베풂을 좌우명으로 삶고 사는 사람이 세계적인 부자가 될 수 있었던 까닭은?");
		bv9.setBookIsbn(9788962605815L);
		bv9.setCategory("경제경영");
		bv9.setPublisher("생각연구소 ");
		
		BookVO bv10 = new BookVO();
		bv10.setBookNo(10);
		bv10.setBookNm("넛지: 파이널 에디션");
		bv10.setBookWtr("리처드 탈러");
		bv10.setBooksub("기후변화 · 코로나19 극복에서 슬러지 · 연금플랜 설계까지,\r\n"
				+ "21세기를 송두리째 바꾼 글로벌 밀리언셀러 『넛지』의 완결판\r\n"
				+ "\r\n"
				+ "전 세계에 ‘넛지’ 열풍을 불러일으킨 글로벌 밀리언셀러 『넛지』가 출간 13년 만에 더욱 강력해진 최종판"
				+ " 『넛지: 파이널 에디션(Nudge: The Final Edition)』으로 다시 태어났다. "
				+ "2008년 미국에서 처음 출간된 이후 인간의 행동 방식과 선택에 대한 관점을 송두리째 뒤집으며"
				+ " 200만 독자들의 선택과 노벨경제학상으로 증명된 금세기 최고의 경제학 고전 『넛지』가, "
				+ "더욱 강력한 선택 설계 아이디어와 최신 사례로 무장한 채 독자들을 찾아온 것이다. "
				+ "서문에서 “낡은 것은 버리고 새로운 것들로 채워 넣었다”라고 밝힌 것처럼, "
				+ "두 저자 리처드 탈러와 캐스 선스타인은 13년 동안 확연히 달라진 세상을 반영해 내용의 절반가량을 완전히 새로 썼다.");
		bv10.setBookIsbn(9788901260679L);
		bv10.setCategory("경제경영");
		bv10.setPublisher("리더스북");

		BookVO bv11 = new BookVO();
		bv11.setBookNo(11);
		bv11.setBookNm("알고 있다는 착각");
		bv11.setBookWtr("질리언 테트");
		bv11.setBooksub("곤경에 빠지는 건 뭔가를 몰라서가 아니다.\r\n"
				+ "뭔가를 확실히 안다는 착각 때문이다\r\n"
				+ "\r\n"
				+ "당연한 것을 의심하고 낯선 진실을 발견하는 인류학자의 사고법");
		bv11.setBookIsbn(9791167740571L);
		bv11.setCategory("경제경영");
		bv11.setPublisher("어크로스");

		BookVO bv12 = new BookVO();
		bv12.setBookNo(12);
		bv12.setBookNm("NFT 레볼루션");
		bv12.setBookWtr("성소라, 롤프 회퍼, 스콧 맥러플린");
		bv12.setBooksub("넥스트 빅 씽 Next Big Thing : NFT 시대가 온다!\r\n"
				+ "\r\n"
				+ "예술·게임·교육·스포츠·금융·유통 등\r\n"
				+ "우리가 창조하고 소비하는 모든 산업과 직접 연결되는 패러다임의 대전환!\r\n"
				+ "\r\n"
				+ "“지금 당장 블록체인 기술이 이끄는 새로운 경제 생태계, NFT에 올라타라”\r\n"
				+ "\r\n"
				+ "요즘 NFT에 관한 뉴스는 하루만 걸러도 따라가기 힘들 정도다. "
				+ "전 세계의 돈이 몰리는 급성장 시장이다 보니 소문도 괴담도 무성하다. "
				+ "예술부터 유통, 금융까지 걸쳐 있는 산업도 너무 다양해서 자신에게 맞는 해설을 찾는 것도 중요하다. "
				+ "그래서 책 한 권에 신뢰할 수 있는 정보와 전망을 정리한 이 책이 더욱 반갑다.");
		bv12.setBookIsbn(9791165216603L);
		bv12.setCategory("경제경영");
		bv12.setPublisher(" 더퀘스트");
		
		BookVO bv13 = new BookVO();
		bv13.setBookNo(13);
		bv13.setBookNm("밥 프록터 부의 확신 ");
		bv13.setBookWtr("밥 프록터");
		bv13.setBooksub("“기억하라! 당신은 부자로 태어났다!” 전설적인 자기계발의 구루 ‘밥 프록터’!\r\n"
				+ "매회 전 석 매진을 기록한 그의 단독 세미나에서만 공개됐던 ‘부의 확신’!");
		bv13.setBookIsbn(9791162542866L);
		bv13.setCategory("자기계발");
		bv13.setPublisher("비즈니스북스");
		
		BookVO bv14 = new BookVO();
		bv14.setBookNo(14);
		bv14.setBookNm("역행자");
		bv14.setBookWtr("자청");
		bv14.setBooksub("\r\n"
				+ "MD 한마디\r\n"
				+ "단 20편의 영상으로 10만 구독자를 넘어서며 화제를 모았던 라이프해커 자청의 첫 책이다. "
				+ "가난을 넘어 경제적 자유와 행복을 얻기까지 저자가 찾아낸 7단계의 성공 원리를 담고 있다. "
				+ "고정관념을 넘어 인생을 레벨 업하고 싶다면 저자의 성공 방식을 만나고 실행해 보자. "
				+ "- 자기계발 MD 김상근\r\n"
				+ "오타쿠 흙수저에서\r\n"
				+ "월 1억 자동 수익을 실현한 무자본 연쇄창업마,\r\n"
				+ "라이프해커 자청의 인생 역주행 공식 대공개\r\n"
				+ "\r\n"
				+ "대부분의 사람들은 유전자와 본성의 명령을 그대로 따르기 때문에 평범함을 벗어날 수 없다. "
				+ "하지만 정작 자신은 이를 모른 채 ‘나는 달라’ 하는 자의식에 사로잡혀서 무한 합리화에 빠져 살아간다. "
				+ "스스로가 얼마나 많은 정신적, 심리적 오류를 저지르는지 알지 못한 채 매일 똑같은 쳇바퀴를 돌 뿐이다. "
				+ "왜 우리는 진짜 자유를 얻지 못하는가? 왜 늘 돈 이야기를 하면서도 평생 돈에 허덕이는가?");
		bv14.setBookIsbn(9788901260716L);
		bv14.setCategory("자기계발");
		bv14.setPublisher("웅진지식하우스");
		
		BookVO bv15 = new BookVO();
		bv15.setBookNo(15);
		bv15.setBookNm("오은영의 화해");
		bv15.setBookWtr("오은영");
		bv15.setBooksub("외면하고 싶었던 마음속 고통과 직면해야 하는 당신에게\r\n"
				+ "오은영 박사가 건네는 따뜻한 위로와 명쾌한 조언\r\n"
				+ "\r\n"
				+ "누구나 인생은 쉽지 않다고 느끼지만, 어린 시절 부모와의 관계에서 모호함과 두려움을 경험한 사람은"
				+ " 유독 살아가는 데 더 큰 어려움을 겪게 된다. 너무 힘들어 주저앉은 당신에게, 충분히 지쳐 있을 당신에게, "
				+ "저자는 나를 알아차리기 위해 아주 조금만 힘을 내어 보라고 말한다. 아무것도 할 수 없던 그때의 당신과 지금의 당신은 다르다. "
				+ "이 책은 그때 상처받았고 지금도 아프다고 말할 수 있는 당신의 내면에 힘이 있음을 믿어 보라며 따뜻한 위로와 함께 명쾌한 조언을 건넨다.");
		bv15.setBookIsbn(9788997396870L);
		bv15.setCategory("자기계발");
		bv15.setPublisher("코리아닷컴");
		
		BookVO bv16 = new BookVO();
		bv16.setBookNo(16);
		bv16.setBookNm("흔해 빠진 이야기는 싫어!2");      
		bv16.setBookWtr("칼리 다비드");                      
		bv16.setBooksub("이 이야기는 기사의 이야기란다.\r\n"
				+ "아, 싫어! 공주를 구하러 가는 기사 얘기는 흔해 빠졌어!\r\n"
				+ "너무 빤하잖아? 게다가 남녀 차별이야!\r\n"
				+ "공주들은 스스로 자기를 구할 수 있단 말이야!\r\n"
				+ "좋아, 좋아. 그럼 이 얘기는 사악한 용을\r\n"
				+ "죽이러 가는 기사 이야기로 하자….\r\n"
				+ "왜 사악한데? 용은 언제나 사악해야 해?\r\n"
				+ "그것도 너무 빤하잖아, 안 그래?");                      
		bv16.setBookIsbn(9791190704397L);                     
		bv16.setCategory("어린이2");                     
		bv16.setPublisher("봄볕");                        
		
		BookVO bv17 = new BookVO();
		bv17.setBookNo(17);
		bv17.setBookNm("황금열광 : 하은경 장편소설2");
		bv17.setBookWtr("하은경");
		bv17.setBooksub("『황금열광』은 금광 재벌인 김 노인이 살해된 수요일 밤의 행적을 좇으며 시작되는 추리소설로"
				+ " 십 대 소년 동재와 사건을 쫓는 강 형사가 중심 화자가 되어 이야기를 펼쳐 나간다. "
				+ "벼락부자의 헛된 꿈을 꾸던 동재가 의도치 않게 시대의 열망에 휘말려 나가는 모습이 소설 속에 흐르는 운명 교향곡을 보듯 흡인력 있는 전개로 짜여 있다. "
				+ "혼란스러운 시대 속 저마다의 열망을 품은 사람들의 모습이 과거를 뛰어넘어 현재의 청년들에게도 ‘돈의 쓰임’과 ‘선택의 무게’에 대한 메시지를 던진다.");
		bv17.setBookIsbn(9788949137018L);
		bv17.setCategory("소설");
		bv17.setPublisher("비룡소");

		BookVO bv18 = new BookVO();
		bv18.setBookNo(18);
		bv18.setBookNm("화장실 냄새를 말끔히 없애려면?2");
		bv18.setBookWtr("윤효식");
		bv18.setBooksub("《용선생의 시끌벅적 과학교실 - 32 혼합물의 분리》 편에서는 우리 주변에 존재하는 "
				+ "다양한 혼합물과 이 혼합물을 분리하여 우리가 원하는 물질을 얻는 방법에 대해 알아봅니다. "
				+ "이를 위해 먼저 우리 주변 물질 중에서 혼합물을 찾아보고, 혼합물울 분리하는 다양한 방법을 단계별로 살펴봅니다. "
				+ "크기 차이나 자석을 이용하는 간단한 방법뿐 아니라 밀도 차이, 끓는점 차이, 용해도 차이, 크로마토그래피를 이용하는 방법까지 알아보지요. "
				+ "이렇게 분리해 낸 물질을 실제로 어떻게 이용하고 있는지까지 살펴보고 나면 독자 어린이들은 물질의 세계를 더욱 잘 이해할 수 있으며, "
				+ "우리가 혼합물의 분리로 얻은 물질을 다양하게 이용하면서 생활을 발전시켜왔다는 것을 과학적으로 이해하게 될 것입니다.");
		bv18.setBookIsbn(97911627322120L);
		bv18.setCategory("어린이");
		bv18.setPublisher("사회평론");
		
		BookVO bv19 = new BookVO();
		bv19.setBookNo(19);
		bv19.setBookNm("홀리데이2");
		bv19.setBookWtr("T. M. 로건");
		bv19.setBooksub("T. M. 로건의 심리 스릴러 『홀리데이』가 아르테에서 출간되었다. "
				+ "T. M. 로건은 자신만의 독특한 문체로 완성한 『리얼 라이즈』로 최고의 데뷔작이라는 평가를 받으며"
				+ " 미국, 이탈리아, 폴란드, 네덜란드 등 10개국에 판권을 계약하여 전 세계의 이목을 집중시켰다. "
				+ "심리스릴러의 신예로 급부상한 그는 두 번째 작품으로 강력한 리벤지 스릴러 『29초』를 선보여 독자들의 기대에 부응하였으며 출간 즉시 베스트셀러에 올랐다. "
				+ "이로써 100만 부의 판매고를 기록한 그의 세 번째 심리스릴러 『홀리데이』는 전작을 뛰어넘은 완벽한 작품이라는 찬사를 받으며, "
				+ "한시도 눈을 뗄 수 없는 서스펜스와 예측할 수 없는 완벽한 결말로 독자들을 강렬하게 매료시킨다. 여름 휴가를 완벽하게 만들어줄 스릴러 책을 찾는다면, "
				+ "단연코 T. M. 로건의 신작 『홀리데이』일 것이다!");
		bv19.setBookIsbn(9788950901929L);
		bv19.setCategory("소설");
		bv19.setPublisher("아르테");
		
		BookVO bv20 = new BookVO();
		bv20.setBookNo(20);
		bv20.setBookNm("디디의 우산2");
		bv20.setBookWtr("황정은");
		bv20.setBooksub("이제 행복해지자, 너의 행복과 더불어\r\n"
				+ "세계라는 빗속에서 황정은이 건네는 우산 같은 소설\r\n"
				+ "\r\n"
				+ "장편소설 『계속해보겠습니다』『百의 그림자』, 소설집 『파씨의 입문』 『아무도 아닌』 등으로 넓고 탄탄한 독자층을 형성한 동시에 평단의 확고한 지지를 받으며"
				+ " 명실공히 한국문학을 대표하는 작가 중 한 사람으로 자리매김한 황정은 작가의 신간 『디디의 우산』이 출간되었다. "
				+ "김유정문학상 수상작 「d」(발표 당시 제목 ‘웃는 남자’)와 『문학3』 웹 연재시 뜨거운 호응을 얻었던 「아무것도 말할 필요가 없다」, "
				+ "인물과 서사는 다르지만 시대상과 주제의식을 공유하며 서로 공명하는 연작 성격의 중편 2편을 묶은 소설집이다. "
				+ "2014년 세월호참사와 2016~17년 촛불혁명이라는 사회적 격변을 배경에 두고 개인의 일상 속에서 ‘혁명’의 새로운 의미를 탐구한 작품들이다."
				+ " 삶과 죽음, 사랑과 인간을 사유하는 깊은 성찰이 마음속 깊이 파고드는 아름다운 문장들과 어우러진 가운데 끝내 압도적인 감동을 선사하는 반가운 신작이다.");
		bv20.setBookIsbn(9788936437541L);
		bv20.setCategory("소설");
		bv20.setPublisher("창비");
		
		BookVO bv21 = new BookVO();
		bv21.setBookNo(21);
		bv21.setBookNm("우리가 빛의 속도로 갈 수 없다면2");
		bv21.setBookWtr("김초엽");
		bv21.setBooksub("지난겨울까지 바이오센서를 만드는 과학도였던 김초엽 작가는, 이제 소설을 쓴다. "
				+ "「관내분실」로 한국과학문학상 중단편부문 대상을 받았다. 필명으로 낸 「우리가 빛의 속도로 갈 수 없다면」도 동시에 상을 받았다. "
				+ "‘한국 SF의 우아한 계보’라 불리며 작품 활동을 시작한 김초엽 작가는 그 후, 더욱 도약했다. "
				+ "자신만이 그려낼 수 있는 김초엽 특유의 작품세계를 보여주었다. "
				+ "투명하고 아름답지만 순진하지만은 않은, 어디에도 없는 그러나 어딘가에 있을 것 같은, 근사한 세계를 손에 잡힐 듯 이야기에 담아냈다.");
		bv21.setBookIsbn(9791190090018L);
		bv21.setCategory("소설");
		bv21.setPublisher("허블");
		
		BookVO bv22 = new BookVO();
		bv22.setBookNo(22);
		bv22.setBookNm("밝은 밤2");
		bv22.setBookWtr("최은영");
		bv22.setBooksub("증조할머니에게서 나로 이어지는 여성 4대의 삶을 담은 소설. "
				+ "1930년대 황해도에서 백정의 딸로 태어나 모진 세월을 살아낸 증조할머니의 시간은 그를 닮은 나에게 전해져 새 숨을 얻고, "
				+ "나의 오늘 또한 과거와의 조우를 통해 다시 쓰인다. "
				+ "부드럽고도 힘있는 문장으로 그린 백 년의 이야기 "
				+ "-소설MD 박형욱");
		bv22.setBookIsbn(9788954681179L);
		bv22.setCategory("소설");
		bv22.setPublisher("문학동네");
		
		BookVO bv23 = new BookVO();
		bv23.setBookNo(23);
		bv23.setBookNm("이기적 유전자 The Selfish Gene2");
		bv23.setBookWtr("리처드 도킨스");
		bv23.setBooksub("현대 생물학의 새로운 지평을 연 세계적인 석학 리처드 도킨스의 대표작 "
				+ "『이기적 유전자』의 40주년 기념판이 출간되었다. "
				+ "진화론의 새로운 패러다임을 제시한 이 책은 다윈의 ‘적자생존과 자연선택’이라는 개념을 유전자 단위로 끌어내려 진화를 설명한다. "
				+ "2013년 영국의 정치평론지 『프로스펙트』지가 독자들의 투표로 선정하는 "
				+ "‘세계 최고의 지성’ 1위에 오른 바 있는 도킨스는 일찍이 촉망받는 젊은 과학자로 간결한 문체와 생생한 비유, "
				+ "논리적인 전개를 갖춘 글로 능력을 인정받아 왔다. "
				+ "도킨스는 자신의 동물행동학 연구를 진화의 역사에서 유전자가 차지하는 중심적 역할에 대한 좀 더 넓은 이론적 맥락과 연결시키기 시작했는데, "
				+ "그 결과가 바로 『이기적 유전자』(초판 1976년, 개정판 1989년, 30주년 기념판 2006년, 40주년 기념판 2016년)다.");
		bv23.setBookIsbn(9788932473901L);
		bv23.setCategory("자연과학");
		bv23.setPublisher("을유문화사");
		
		BookVO bv24 = new BookVO();
		bv24.setBookNo(24);
		bv24.setBookNm("기브 앤 테이크. 주는 사람이 성공한다2");
		bv24.setBookWtr("애덤 그랜트");
		bv24.setBooksub("양보하고, 배려하고, 베풀고, 희생하고, 조건 없이 주는 사람이\r\n"
				+ "어떻게 성공 사다리의 꼭대기에 올랐을까?\r\n"
				+ "\r\n"
				+ "[포천]이 선정한 세계 최고의 인맥을 쌓은 사람은 누구이며, 그 비결은 무엇인가? "
				+ "말더듬이 신참 변호사가 어떻게 재판에서 청산유수 베테랑 변호사를 이겼을까? "
				+ "미국 역사상 가장 위대한 대통령의 인사 시스템에는 어떤 비밀이 숨어 있는가? "
				+ "베풂을 좌우명으로 삶고 사는 사람이 세계적인 부자가 될 수 있었던 까닭은?");
		bv24.setBookIsbn(9788962605815L);
		bv24.setCategory("경제경영");
		bv24.setPublisher("생각연구소 ");
		
		BookVO bv25 = new BookVO();
		bv25.setBookNo(25);
		bv25.setBookNm("넛지: 파이널 에디션2");
		bv25.setBookWtr("리처드 탈러");
		bv25.setBooksub("기후변화 · 코로나19 극복에서 슬러지 · 연금플랜 설계까지,\r\n"
				+ "21세기를 송두리째 바꾼 글로벌 밀리언셀러 『넛지』의 완결판\r\n"
				+ "\r\n"
				+ "전 세계에 ‘넛지’ 열풍을 불러일으킨 글로벌 밀리언셀러 『넛지』가 출간 13년 만에 더욱 강력해진 최종판"
				+ " 『넛지: 파이널 에디션(Nudge: The Final Edition)』으로 다시 태어났다. "
				+ "2008년 미국에서 처음 출간된 이후 인간의 행동 방식과 선택에 대한 관점을 송두리째 뒤집으며"
				+ " 200만 독자들의 선택과 노벨경제학상으로 증명된 금세기 최고의 경제학 고전 『넛지』가, "
				+ "더욱 강력한 선택 설계 아이디어와 최신 사례로 무장한 채 독자들을 찾아온 것이다. "
				+ "서문에서 “낡은 것은 버리고 새로운 것들로 채워 넣었다”라고 밝힌 것처럼, "
				+ "두 저자 리처드 탈러와 캐스 선스타인은 13년 동안 확연히 달라진 세상을 반영해 내용의 절반가량을 완전히 새로 썼다.");
		bv25.setBookIsbn(9788901260679L);
		bv25.setCategory("경제경영");
		bv25.setPublisher("리더스북");

		BookVO bv26 = new BookVO();
		bv26.setBookNo(26);
		bv26.setBookNm("알고 있다는 착각2");
		bv26.setBookWtr("질리언 테트");
		bv26.setBooksub("곤경에 빠지는 건 뭔가를 몰라서가 아니다.\r\n"
				+ "뭔가를 확실히 안다는 착각 때문이다\r\n"
				+ "\r\n"
				+ "당연한 것을 의심하고 낯선 진실을 발견하는 인류학자의 사고법");
		bv26.setBookIsbn(9791167740571L);
		bv26.setCategory("경제경영2");
		bv26.setPublisher("어크로스");

		BookVO bv27 = new BookVO();
		bv27.setBookNo(27);
		bv27.setBookNm("NFT 레볼루션2");
		bv27.setBookWtr("성소라, 롤프 회퍼, 스콧 맥러플린");
		bv27.setBooksub("넥스트 빅 씽 Next Big Thing : NFT 시대가 온다!\r\n"
				+ "\r\n"
				+ "예술·게임·교육·스포츠·금융·유통 등\r\n"
				+ "우리가 창조하고 소비하는 모든 산업과 직접 연결되는 패러다임의 대전환!\r\n"
				+ "\r\n"
				+ "“지금 당장 블록체인 기술이 이끄는 새로운 경제 생태계, NFT에 올라타라”\r\n"
				+ "\r\n"
				+ "요즘 NFT에 관한 뉴스는 하루만 걸러도 따라가기 힘들 정도다. "
				+ "전 세계의 돈이 몰리는 급성장 시장이다 보니 소문도 괴담도 무성하다. "
				+ "예술부터 유통, 금융까지 걸쳐 있는 산업도 너무 다양해서 자신에게 맞는 해설을 찾는 것도 중요하다. "
				+ "그래서 책 한 권에 신뢰할 수 있는 정보와 전망을 정리한 이 책이 더욱 반갑다.");
		bv27.setBookIsbn(9791165216603L);
		bv27.setCategory("경제경영");
		bv27.setPublisher(" 더퀘스트");
		
		BookVO bv28 = new BookVO();
		bv28.setBookNo(28);
		bv28.setBookNm("밥 프록터 부의 확신2");
		bv28.setBookWtr("밥 프록터");
		bv28.setBooksub("“기억하라! 당신은 부자로 태어났다!” 전설적인 자기계발의 구루 ‘밥 프록터’!\r\n"
				+ "매회 전 석 매진을 기록한 그의 단독 세미나에서만 공개됐던 ‘부의 확신’!");
		bv28.setBookIsbn(9791162542866L);
		bv28.setCategory("자기계발");
		bv28.setPublisher("비즈니스북스");
		
		BookVO bv29 = new BookVO();
		bv29.setBookNo(29);
		bv29.setBookNm("역행자2");
		bv29.setBookWtr("자청");
		bv29.setBooksub("\r\n"
				+ "MD 한마디\r\n"
				+ "단 20편의 영상으로 10만 구독자를 넘어서며 화제를 모았던 라이프해커 자청의 첫 책이다. "
				+ "가난을 넘어 경제적 자유와 행복을 얻기까지 저자가 찾아낸 7단계의 성공 원리를 담고 있다. "
				+ "고정관념을 넘어 인생을 레벨 업하고 싶다면 저자의 성공 방식을 만나고 실행해 보자. "
				+ "- 자기계발 MD 김상근\r\n"
				+ "오타쿠 흙수저에서\r\n"
				+ "월 1억 자동 수익을 실현한 무자본 연쇄창업마,\r\n"
				+ "라이프해커 자청의 인생 역주행 공식 대공개\r\n"
				+ "\r\n"
				+ "대부분의 사람들은 유전자와 본성의 명령을 그대로 따르기 때문에 평범함을 벗어날 수 없다. "
				+ "하지만 정작 자신은 이를 모른 채 ‘나는 달라’ 하는 자의식에 사로잡혀서 무한 합리화에 빠져 살아간다. "
				+ "스스로가 얼마나 많은 정신적, 심리적 오류를 저지르는지 알지 못한 채 매일 똑같은 쳇바퀴를 돌 뿐이다. "
				+ "왜 우리는 진짜 자유를 얻지 못하는가? 왜 늘 돈 이야기를 하면서도 평생 돈에 허덕이는가?");
		bv29.setBookIsbn(9788901260716L);
		bv29.setCategory("자기계발");
		bv29.setPublisher("웅진지식하우스");
		
		BookVO bv30 = new BookVO();
		bv30.setBookNo(30);
		bv30.setBookNm("오은영의 화해2");
		bv30.setBookWtr("오은영");
		bv30.setBooksub("외면하고 싶었던 마음속 고통과 직면해야 하는 당신에게\r\n"
				+ "오은영 박사가 건네는 따뜻한 위로와 명쾌한 조언\r\n"
				+ "\r\n"
				+ "누구나 인생은 쉽지 않다고 느끼지만, 어린 시절 부모와의 관계에서 모호함과 두려움을 경험한 사람은"
				+ " 유독 살아가는 데 더 큰 어려움을 겪게 된다. 너무 힘들어 주저앉은 당신에게, 충분히 지쳐 있을 당신에게, "
				+ "저자는 나를 알아차리기 위해 아주 조금만 힘을 내어 보라고 말한다. 아무것도 할 수 없던 그때의 당신과 지금의 당신은 다르다. "
				+ "이 책은 그때 상처받았고 지금도 아프다고 말할 수 있는 당신의 내면에 힘이 있음을 믿어 보라며 따뜻한 위로와 함께 명쾌한 조언을 건넨다.");
		bv30.setBookIsbn(9788997396870L);
		bv30.setCategory("자기계발");
		bv30.setPublisher("코리아닷컴");
		
		bookList.add(bv1);
		bookList.add(bv2);
		bookList.add(bv3);
		bookList.add(bv4);
		bookList.add(bv5);
		bookList.add(bv6);
		bookList.add(bv7);
		bookList.add(bv8);
		bookList.add(bv9);
		bookList.add(bv10);
		bookList.add(bv11);
		bookList.add(bv12);
		bookList.add(bv13);
		bookList.add(bv14);
		bookList.add(bv15);
		bookList.add(bv16);
		bookList.add(bv17);
		bookList.add(bv18);
		bookList.add(bv19);
		bookList.add(bv20);
		bookList.add(bv21);
		bookList.add(bv22);
		bookList.add(bv23);
		bookList.add(bv24);
		bookList.add(bv25);
		bookList.add(bv26);
		bookList.add(bv27);
		bookList.add(bv28);
		bookList.add(bv29);
		bookList.add(bv30);

		return bookList;
	}
	
	// 파라마터 BookVO 객체의 releaseDate(출간일)을 setting하는 메서드
	public Date getBookReleaseDate(BookVO bv) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String strDate = "2022.05.01";
	    Date releaseDate = null;
		try {
			releaseDate = new Date(sdf.parse(strDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("net.mbiz.library.data.MakeBookList : 날짜 데이터 포맷 중 오류");
		}
	    
	    bv.setRegistDate(releaseDate);
	    
		return releaseDate;
	}
	
	public List<BookVO> addReleaseDate()  {
		List<BookVO> bkList = addBookData();
		for (BookVO bv : bkList) {
			bv.setReleaseDate(getBookReleaseDate(bv));
			bv.setIsBorrowed(1);
		}
		System.err.println("net.mbiz.library.data.MakeBookList : addReleaseDate(). 도서 데이터 추가완료");
		return bkList;
	}
	
}
