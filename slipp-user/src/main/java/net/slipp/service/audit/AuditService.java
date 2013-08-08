package net.slipp.service.audit;

import javax.annotation.Resource;

import net.slipp.dao.audit.AuditDao;
import net.slipp.domain.audit.AuditObject;

import org.springframework.stereotype.Service;

@Service
public class AuditService {
	@Resource(name = "auditDao")
	private AuditDao auditDao;

	public void log(AuditObject audit) {
		auditDao.log(audit);
	}

	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}
}
