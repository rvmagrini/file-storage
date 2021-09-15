package com.rvmagrini.filestorageserver.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Business Logic

@Service
public class UserProfileService {
	
	private final UserProfileDataAccessService userProfileDataAccessService;
	
	@Autowired
	public UserProfileService(UserProfileDataAccessService userProfileDataAccessService) {
		this.userProfileDataAccessService = userProfileDataAccessService;
	}
	
	List<UserProfile> getUserProfiles() {
		return userProfileDataAccessService.getUserProfiles();
	}
	
	// Now we can take UserProfileService and use it in Controller

}
