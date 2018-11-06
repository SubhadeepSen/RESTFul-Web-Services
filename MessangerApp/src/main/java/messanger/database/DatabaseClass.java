package messanger.database;

import java.util.HashMap;
import java.util.Map;

import messanger.model.Comment;
import messanger.model.Message;
import messanger.model.Profile;

public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();

	public static Map<Long, Message> getMessages() {
		Message m1 = new Message(101, "Hello World", "Subhadeep");
		Map<Long, Comment> comments = new HashMap<>();
		comments.put(1L, new Comment(1, "my comment", "sunny"));
		m1.setComments(comments);
		messages.put(101L, m1);
		messages.put(202L, new Message(202, "Hey! What's up?", "Sunny"));
		messages.put(303L, new Message(303, "Application is running", "Subhadeep"));
		return messages;
	}

	public static Map<String, Profile> getProfiles() {
		profiles.put("Subhadeep", new Profile(11, "Subhadeep", "Subhadeep", "Sen"));
		profiles.put("Bhaskar", new Profile(22, "Bhaskar", "Bhaskar", "Auddy"));
		return profiles;
	}

}
