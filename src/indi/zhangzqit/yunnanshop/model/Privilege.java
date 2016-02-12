package indi.zhangzqit.yunnanshop.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;

public class Privilege implements java.io.Serializable {

	private Integer id;

	private String text;

	private Set<Privilege> children;

	private Privilege parent;

	private Collection<ConfigAttribute> roleSet;

	public Collection<ConfigAttribute> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Collection<ConfigAttribute> roleSet) {
		this.roleSet = roleSet;
	}

	private Boolean checked; // ������û�ж�Ӧ���ֶ�

	private String state = "closed"; // ������û�ж�Ӧ���ֶ�

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", text=" + text + ", checked="
				+ checked + ", state=" + state + "]";
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Privilege() {
	}

	public Privilege(int id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return this.checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}