package com.rvmagrini.filestorageserver.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rvmagrini.filestorageserver.datastore.FakeUserProfileDataStore;

@Repository
public class UserProfileDataAccessService {
	
	private final FakeUserProfileDataStore fakeUserProfileDataStore;
	
	@Autowired
	public UserProfileDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore) {
		this.fakeUserProfileDataStore = fakeUserProfileDataStore;
	}
	
	List<UserProfile> getUserProfiles() {
		return fakeUserProfileDataStore.getUserProfiles();
	}
	
	// Now we can use Dependency Injection to change the FakeUserProfileDataStore Class and
	// then change implementations, from the fake DB to a real DB
	
}
