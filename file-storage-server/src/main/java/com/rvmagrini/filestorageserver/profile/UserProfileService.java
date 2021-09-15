package com.rvmagrini.filestorageserver.profile;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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


	public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
		// Once we have the file from frontend, we can implement the logic:
		// 1. Check if image is not empty
		// 2. Check if file is an image
		// 3. Check if user exists in our DB
		// 4. Grab some metadata from file if any
		// 5. Store the img in S3 and update DB (userProfileImageLink) with S3 img link
	}
	

}
