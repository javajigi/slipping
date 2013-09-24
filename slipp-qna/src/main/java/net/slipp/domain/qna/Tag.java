package net.slipp.domain.qna;

public class Tag {
	private Long tagId;

	private String name;
	
	private boolean pooled;

	private int taggedCount = 0;

	private Tag(String name, boolean pooled) {
		this.name = name;
		this.pooled = pooled;
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
	
	public boolean isPooled() {
		return pooled;
	}
	
	public void tagged() {
		taggedCount += 1;
	}
	
	public void detagged() {
		taggedCount -= 1;
	}
	
	public static Tag pooledTag(String name) {
		return new Tag(name, true);
	}
	
	public static Tag newTag(String name) {
		return new Tag(name, false);
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
