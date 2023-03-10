package semi.team.baro.mercenary.model.vo;

public class Mercenary {
	private int mercenaryNo; //게시글번호
	private int memberNo; //회원번호(작성자)
	private String memberId; //회원아이디
	private String location; //지역
	private String groundName; //구장이름
	private String gameDate; //게임날짜
	private int gameTime; //게임시간
	private String mercenaryContent; //게시글내용
	private int readCount; //조회수
	private String regDate; //작성일
	private int mercenaryWhether; //용병모집상태(0:모집중 / 1:모집완료)
	private int mercenaryPay; //참가비
	private int level; //실력
	private String groundLocation;
	private String groundLat;
	private String groundLng;
	private int groundNo;
	
	public Mercenary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMercenaryNo() {
		return mercenaryNo;
	}
	public void setMercenaryNo(int mercenaryNo) {
		this.mercenaryNo = mercenaryNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGroundName() {
		return groundName;
	}
	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}
	public String getGameDate() {
		return gameDate;
	}
	public void setGameDate(String gameDate) {
		this.gameDate = gameDate;
	}
	public int getGameTime() {
		return gameTime; 
	}
	public String getGameShowTime() {
		return gameTime+" : 00 ~ "+(gameTime+2)+" : 00";
	}
	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}
	public String getMercenaryContent() {
		return mercenaryContent;
	}
	public String getMercenaryContentBr() {
		return mercenaryContent.replaceAll("\r\n", "<br>");
	}
	public void setMercenaryContent(String mercenaryContent) {
		this.mercenaryContent = mercenaryContent;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getMercenaryWhether() {
		return mercenaryWhether;
	}
	public void setMercenaryWhether(int mercenaryWhether) {
		this.mercenaryWhether = mercenaryWhether;
	}
	public int getMercenaryPay() {
		return mercenaryPay;
	}
	public void setMercenaryPay(int mercenaryPay) {
		this.mercenaryPay = mercenaryPay;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getGroundLocation() {
		return groundLocation;
	}
	public void setGroundLocation(String groundLocation) {
		this.groundLocation = groundLocation;
	}
	public String getGroundLat() {
		return groundLat;
	}
	public void setGroundLat(String groundLat) {
		this.groundLat = groundLat;
	}
	public String getGroundLng() {
		return groundLng;
	}
	public void setGroundLng(String groundLng) {
		this.groundLng = groundLng;
	}
	public int getGroundNo() {
		return groundNo;
	}
	public void setGroundNo(int groundNo) {
		this.groundNo = groundNo;
	}
	
	
}
