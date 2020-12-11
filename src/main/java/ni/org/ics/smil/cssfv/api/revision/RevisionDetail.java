package ni.org.ics.smil.cssfv.api.revision;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import lombok.Data;

@Data
@Entity
@RevisionEntity(MyRevisionListener.class)
public class RevisionDetail extends DefaultRevisionEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7133747305178248387L;
	
	@Column(name="username")
	private String username;

}
