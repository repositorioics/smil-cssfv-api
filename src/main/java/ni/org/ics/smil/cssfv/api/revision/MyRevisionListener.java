package ni.org.ics.smil.cssfv.api.revision;

import org.hibernate.envers.RevisionListener;

public class MyRevisionListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		RevisionDetail rev = (RevisionDetail) revisionEntity;
		rev.setUsername("msalinas");
	}
}
