package indi.zhangzqit.yunnanshop.model;

public class Status implements java.io.Serializable {

	// Fields

	private Integer id;
	private String status;

	// Constructors

	/** default constructor */
	public Status() {
	}

	public Status(Integer id) {
		super();
		this.id = id;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}