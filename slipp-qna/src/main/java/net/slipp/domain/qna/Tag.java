package net.slipp.domain.qna;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tagId;
	
	@Column(name = "name", length = 50, unique = true, nullable = false)
	private String name;
	
	@Column(name = "pooled", nullable = false)
	private boolean pooled;

	private int taggedCount = 0;

	private Tag(String name, boolean pooled) {
		this.name = name;
		this.pooled = pooled;
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
		return "Tag [name=" + name + ", taggedCount=" + taggedCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (pooled ? 1231 : 1237);
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
		if (pooled != other.pooled)
			return false;
		return true;
	}
}
