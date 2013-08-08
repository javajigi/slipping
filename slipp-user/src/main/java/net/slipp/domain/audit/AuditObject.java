package net.slipp.domain.audit;

import java.util.Date;

public class AuditObject {
	private Long id;
	private String who;
	private Date whenn;
	private String resource;
	private String action;

	public AuditObject() {
	}

	public AuditObject(String resource, String action) {
		initWho();
		initWhen();
		this.resource = resource;
		this.action = action;
	}

	private void initWho() {
		this.who = (String) System.getProperties().get("user.name");
	}

	private void initWhen() {
		this.whenn = new Date();
	}

	public void setId(Long i) {
		id = i;
	}

	public Long getId() {
		return id;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getWho() {
		return who;
	}

	public void setWhenn(Date when) {
		this.whenn = when;
	}

	public Date getWhenn() {
		return whenn;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((whenn == null) ? 0 : whenn.hashCode());
		result = prime * result + ((who == null) ? 0 : who.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuditObject other = (AuditObject) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (whenn == null) {
			if (other.whenn != null)
				return false;
		} else if (!whenn.equals(other.whenn))
			return false;
		if (who == null) {
			if (other.who != null)
				return false;
		} else if (!who.equals(other.who))
			return false;
		return true;
	}
}
