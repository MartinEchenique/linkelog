-- Drop table if exists
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS profile;

-- Create profile table
CREATE TABLE profile (
    userid INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(40) NOT NULL,
    lastname VARCHAR(40) NOT NULL,
    companyname VARCHAR(40),
    profilepictureurl TEXT,
    userrole VARCHAR(40),
    username VARCHAR(40) NOT NULL,
    password VARCHAR NOT NULL
);

-- Create post table
CREATE TABLE post (
    postid INT PRIMARY KEY AUTO_INCREMENT,
    userprofileid INT NOT NULL,
    posttext TEXT NOT NULL,
    postimgurl TEXT NOT NULL,
    pubdate TIMESTAMP NOT NULL,
    FOREIGN KEY (userprofileid) REFERENCES profile(userid)
);

-- Create comments table
CREATE TABLE comments (
    commentid INT PRIMARY KEY AUTO_INCREMENT,
    commenttext TEXT,
    pubdate TIMESTAMP  NOT NULL,
    postid INT  NOT NULL,
    autorid INT  NOT NULL,
    FOREIGN KEY (autorid) REFERENCES profile(userid),
    FOREIGN KEY (postid) REFERENCES post(postid)
);
