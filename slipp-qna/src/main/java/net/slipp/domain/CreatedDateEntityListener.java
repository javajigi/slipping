package net.slipp.domain;

import java.util.Date;

import javax.persistence.PrePersist;

public class CreatedDateEntityListener {

	@PrePersist
	public void prePersist(HasCreatedDate hcd) {
		hcd.setCreatedDate(new Date());
	}
}
