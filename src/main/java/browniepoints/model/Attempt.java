package main.java.browniepoints.model;

public class Attempt {
	private Integer uid;
	private Integer qid;
	private String result;
	private Long date;
	private Integer vid;
	
	public Attempt(Integer uid, Integer qid, String result, Long date, Integer vid) {
		super();
		this.uid = uid;
		this.qid = qid;
		this.result = result;
		this.date = date;
		this.vid = vid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
}