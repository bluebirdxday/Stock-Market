package project.mini.dto;

import java.util.ArrayList;
import java.util.List;

public class Event {
	private String eventName; // 정보 발생 문자열
	private String[] increase = null; // 상승 종목 배열
	private String[] decrease = null; // 하락 종목 배열

	public Event() {}

	public Event(String eventName, String[] increase, String[] decrease) {
		this.eventName = eventName;
		this.increase = increase;
		this.decrease = decrease;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String[] getIncrease() {
		return increase;
	}

	public void setIncrease(String[] increase) {
		this.increase = increase;
	}

	public String[] getDecrease() {
		return decrease;
	}

	public void setDecrease(String[] decrease) {
		this.decrease = decrease;
	}
	
	public static List<Event> initEvents() {
		List<Event> events = new ArrayList<>(); 
		events.add(new Event("코로나 바이러스가 점차 확산되기 시작합니다.",
				new String[]{"한미약품","CJ","애플"},
				new String[] {"대한항공","아모레퍼시픽", "KB 금융","SM"}));
		events.add(new Event("코로나 바이러스 관련 규제가 완화될 예정입니다.",
				new String[]{"대한항공","아모레퍼시픽", "KB 금융","SM"},
				new String[] {"한미약품","CJ","애플"}));
		events.add(new Event("화이자가 코로나 백신 중 첫 FDA 승인을 받았습니다.",
				new String[]{"삼성바이오로직스","하이브","모두투어"},
				new String[] {"삼성전자"}));
		events.add(new Event("한 오리농장에서 조류 인플루엔자 의심 축이 발생되었습니다.",
				new String[]{"종목없음"},
				new String[] {"오뚜기"}));
		events.add(new Event("하이브리드 차량 구매자에 대한 취득세 면제 조치를 시행할 예정입니다.",
				new String[]{"기아"},
				new String[] {"종목없음"}));
		events.add(new Event("하이브리드 차량 화재가 발생하였습니다.",
				new String[]{"DB손해보험"},
				new String[] {"현대모비스","SK 하이닉스"}));
		events.add(new Event("자율주행 4단계 성공 소식이 들려옵니다.",
				new String[]{"테슬라"},
				new String[] {"종목없음"}));
		events.add(new Event("자율주행 주행 중 사고가 발생했다는 소식이 있습니다.",
				new String[]{"종목없음"},
				new String[] {"테슬라"}));
		events.add(new Event("러시아와 우크라이나 전쟁이 발발 될 기미가 보입니다.",
				new String[]{"CJ","코스모화학"},
				new String[] {"삼성바이오로직스", "대한항공", "테슬라", "기아", "SM", "DB하이텍", "삼성전자", "미래에셋대우 증권", "LH", "DB손해보험", "코스모화학", "오뚜기", "LG 생활건강"}));
		events.add(new Event("신도시 개발 사업을 추진할 예정입니다.",
				new String[]{"현대건설"},
				new String[] {"종목없음"}));
		events.add(new Event("Open API가 개발한 인공지능 챗봇 chat GPT가 출시되었습니다.",
				new String[]{"DB하이텍","애플"},
				new String[] {"종목없음"}));
		events.add(new Event("SNS 상에서 메타버스가 유행할 조짐이 보입니다.",
				new String[]{"애플","하이브"},
				new String[] {"종목없음"}));
		events.add(new Event("물가가 상승하고, 금리가 인상되었습니다.",
				new String[]{"종목없음"},
				new String[] {"한미약품", "모두투어", "테슬라", "기아", "SM", "DB하이텍", "애플", "미래에셋대우 증권", "LH", "DB손해보험", "SK종합화학", "오뚜기", "LG 생활건강"}));
		events.add(new Event("금리가 인하될 예정입니다.",
				new String[] {"한미약품", "모두투어", "테슬라", "기아", "SM", "DB하이텍", "애플", "미래에셋대우 증권", "LH", "DB손해보험", "SK종합화학", "오뚜기", "LG 생활건강"},
				new String[] {"종목없음"}));
		events.add(new Event("모 아이돌의 열애설이 발표될 예정입니다.",
				new String[]{"종목없음"},
				new String[] {"하이브"}));
		events.add(new Event("냉동식품 속에서 이물질을 발견했다는 신고가 여럿 들어오고 있습니다.",
				new String[]{"종목없음"},
				new String[] {"CJ"}));
		events.add(new Event("대한항공과 아시아나 합병의 조짐?!",
				new String[]{"대한항공"},
				new String[] {"종목없음"}));
		events.add(new Event("모 회사에서 SM을 인수할 것이라는 소식이 들려옵니다.",
				new String[]{"SM"},
				new String[] {"종목없음"}));
		events.add(new Event("중고폰 거래도 키오스크로 간편하게?",
				new String[]{"애플","SK 하이닉스"},
				new String[] {"종목없음"}));	
		return events;
	}
}
