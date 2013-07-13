package main.java.browniepoints.model;

public class Voucher {
	private Integer vid;
	private Integer cid;
	private Integer winner_uid;
	private String code;
	private Long date;
	private String status;

	public Voucher(Integer cid, Integer winner_uid, String code, Long date,
			String status) {
		super();
		this.cid = cid;
		this.winner_uid = winner_uid;
		this.code = code;
		this.date = date;
		this.status = status;
	}

	public Voucher(Integer vid, Integer cid, Integer winner_uid, String code,
			Long date, String status) {
		super();
		this.vid = vid;
		this.cid = cid;
		this.winner_uid = winner_uid;
		this.code = code;
		this.date = date;
		this.status = status;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getWinner_uid() {
		return winner_uid;
	}

	public void setWinner_uid(Integer winner_uid) {
		this.winner_uid = winner_uid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
