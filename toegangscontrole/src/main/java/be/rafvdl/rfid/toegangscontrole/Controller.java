package be.rafvdl.rfid.toegangscontrole;

import java.util.ArrayList;
import java.util.List;

import be.rafvdl.rfid.api.Tag;

public class Controller {

	private List<Tag> allowed = new ArrayList<Tag>();

	public void addTag(Tag tag) {
		if (!allowed.contains(tag))
			allowed.add(tag);
	}

	public void removeTag(Tag tag) {
		allowed.remove(tag);
	}

	public boolean isAllowed(Tag tag) {
		return allowed.contains(tag);
	}

}
