package com.rvmagrini.filestorageserver.bucket;

public enum BucketName {
	
	PROFILE_IMAGE("rvmagrini-file-storage");
	
	private final String bucketName;

	private BucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBucketName() {
		return bucketName;
	}

}
