# File Storage Application 
## using Spring Boot, React and AWS S3

### Development Process:

### 1: [INITIALIZING]
TASKS:
- [1.1] Create Spring Boot Project, Java AWS SDK dependency and AmazonConfig
- [1.2] Create AWS S3 Bucket and BucketName Class

### 2: [SERVICE-API]
TASKS:
- [2.1] Create Service to store files to the Bucket
- [2.2] Create UserProfile Model Entity
- [2.3] Create fake DB Repository to return fake UserProfiles
- [2.4] Create REST API Controller and Service Layers
- [2.5] Implement the file upload End-point

### 3: [WEB]
TASKS:
- [3.1] Create React App and install Axios
- [3.2] Connect React with REST API
- [3.3] Render User Profile Data
- [3.4] Install and implement React-Dropzone
- [3.5] Implement UI logic to send files to the server

### 4: [SERVICE-DB]
TASKS:
- [4.1] Validate and insert files into AWS-S3 Bucket

### 5: [DB-WEB]
TASKS:
[5.1] Implement service to download image stored in AWS-S3
[5.2] Implement download image on frontend
