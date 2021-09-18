package com.rvmagrini.filestorageserver.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.rvmagrini.filestorageserver.profile.UserProfile;



@Repository
public class FakeUserProfileDataStore {
	
	private static final List<UserProfile> USER_PROFILES = new ArrayList<>();
	
	static {
		USER_PROFILES.add(new UserProfile(UUID.fromString("289771e6-87c1-40a4-b928-44168878b602"), "johnmayall", null));
		USER_PROFILES.add(new UserProfile(UUID.fromString("993d89be-6a2c-47f6-9254-d4968b614086"), "jjcale", null));
	}
	
	public List<UserProfile> getUserProfiles() {
		return USER_PROFILES;
	}

}
