package main.java.browniepoints.model;

public class CompositeQuestion implements Cloneable {
	private Question q = new Question();
	private Coupon c = new Coupon();
	private Restaurant r = new Restaurant();
	public Question getQ() {
		return q;
	}
	@Override
	public String toString() {
		return "CompositeQuestion [q=" + q + ", c=" + c + ", r=" + r + "]";
	}
	public void setQ(Question q) {
		this.q = q;
	}
	public Coupon getC() {
		return c;
	}
	public void setC(Coupon c) {
		this.c = c;
	}
	public Restaurant getR() {
		return r;
	}
	public void setR(Restaurant r) {
		this.r = r;
	}
	
	public CompositeQuestion copyOf() throws CloneNotSupportedException {
		return (CompositeQuestion) this.clone();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}