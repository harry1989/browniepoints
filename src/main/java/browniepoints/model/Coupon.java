package main.java.browniepoints.model;

public class Coupon {
	@Override
	public String toString() {
		return "Coupon [cid=" + cid + ", qid=" + qid + ", title=" + title
				+ ", desc=" + desc + ", discount=" + discount + ", points="
				+ points + ", quota=" + quota + ", start_date=" + start_date
				+ ", end_date=" + end_date + "]";
	}

	private Integer cid;
	private Integer qid;
	private String title;
	private String desc;
	private Integer discount;
	private Integer points;
	private Integer quota;
	private Long start_date;
	private Long end_date;

	public Coupon() {
	}

	public Coupon(Integer cid, Integer qid, String title, String desc,
			Integer quota, Integer discount, Integer points, Long start_date,
			Long end_date) {
		super();
		this.cid = cid;
		this.qid = qid;
		this.title = title;
		this.desc = desc;
		this.discount = discount;
		this.points = points;
		this.start_date = start_date;
		this.end_date = end_date;
		this.quota = quota;
	}

	public Coupon(Integer qid, String title, String desc, Integer quota,
			Integer discount, Integer points, Long start_date, Long end_date) {
		super();
		this.qid = qid;
		this.title = title;
		this.desc = desc;
		this.discount = discount;
		this.points = points;
		this.start_date = start_date;
		this.end_date = end_date;
		this.quota = quota;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Long getStart_date() {
		return start_date;
	}

	public void setStart_date(Long start_date) {
		this.start_date = start_date;
	}

	public Long getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Long end_date) {
		this.end_date = end_date;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public boolean hasCid() {
		return this.cid != null;
	}
}