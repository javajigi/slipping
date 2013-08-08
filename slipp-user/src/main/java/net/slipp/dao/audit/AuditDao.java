package net.slipp.dao.audit;

import net.slipp.domain.audit.AuditObject;

public interface AuditDao {
	int log(AuditObject audit);
}
