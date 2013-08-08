package net.slipp.dao.audit;

import net.slipp.domain.audit.AuditObject;
import net.slipp.support.AbstractDaoSupport;

import org.springframework.stereotype.Repository;

@Repository("auditDao")
public class SpringJdbcAuditDao  extends AbstractDaoSupport implements AuditDao {
	public int log(AuditObject audit) {
		String query = "INSERT INTO AUDIT (who, whenn, resource, action) VALUES(?, ?, ?, ?)";

		return getJdbcTemplate()
				.update(query, audit.getWho(), audit.getWhenn(), audit.getResource(), audit.getAction());
	}
}
