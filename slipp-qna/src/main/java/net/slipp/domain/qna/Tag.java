package net.slipp.domain.qna;


public class Tag {
	private Long tagId;

	private String name;

	private int taggedCount = 0;

	public Tag() {
	}

	public Tag(String name) {
		this.name = name;
	}

	public Long getTagId() {
		return tagId;
	}

	public String getName() {
		return name;
	}

	public int getTaggedCount() {
		return taggedCount;
	}
	
	public void tagged() {
		taggedCount += 1;
	}
	
	public void detagged() {
		taggedCount -= 1;
	}

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", name=" + name + ", taggedCount=" + taggedCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
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
		Tag other = (Tag) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tagId == null) {
			if (other.tagId != null)
				return false;
		} else if (!tagId.equals(other.tagId))
			return false;
		return true;
	}
}
