package com.rvmagrini.filestorageserver.profile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rvmagrini.filestorageserver.bucket.BucketName;
import com.rvmagrini.filestorageserver.filestore.FileStore;

// Business Logic

@Service
public class UserProfileService {
	
	private final UserProfileDataAccessService userProfileDataAccessService;
	private final FileStore fileStore;
	
	@Autowired
	public UserProfileService(
			UserProfileDataAccessService userProfileDataAccessService, 
			FileStore fileStore) {
		this.userProfileDataAccessService = userProfileDataAccessService;
		this.fileStore = fileStore;
	}
	
	List<UserProfile> getUserProfiles() {
		return userProfileDataAccessService.getUserProfiles();
	}
	
	// Now we can take UserProfileService and use it in Controller


	public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
		// Once we have the file from front-end, we can implement the logic:
		
		// 1. Check if image is not empty
		if (file.isEmpty()) {
			throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + " ]");
		}
		
		// 2. Check if file is an image
		if (!Arrays.asList(org.apache.http.entity.ContentType.IMAGE_JPEG.getMimeType(), 
				org.apache.http.entity.ContentType.IMAGE_PNG.getMimeType(),
				org.apache.http.entity.ContentType.IMAGE_GIF.getMimeType())
				.contains(file.getContentType())) {
			throw new IllegalStateException("File must be an image [ " + file.getContentType() + " ]");
		}
		
		// 3. Check if user exists in our DB
		UserProfile user = userProfileDataAccessService
			.getUserProfiles()
			.stream()
			.filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
		
		// 4. Grab some metadata from file if any
		Map<String, String> metadata = new HashMap<>();
		metadata.put("Content-Type", file.getContentType());
		metadata.put("Content-Lenght", String.valueOf(file.getSize()));
		
		// 5. Store the img in S3 and update DB (userProfileImageLink) with S3 img link
		String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
		String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
	
		try {
			fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
			user.setUserProfileImageLink(fileName);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	byte[] downloadUserProfileImage(UUID userProfileId) {
		// 1. Check if user exists in our DB
		UserProfile user = userProfileDataAccessService
			.getUserProfiles()
			.stream()
			.filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
		
		String path = String.format("%s/%s", 
				BucketName.PROFILE_IMAGE.getBucketName(), 
				user.getUserProfileId());
		
		return user.getUserProfileImageLink()
				.map(key -> fileStore.download(path, key))
				.orElse(new byte[0]);
	}
	

}
