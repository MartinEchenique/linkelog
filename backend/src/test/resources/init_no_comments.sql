-- Drop table if exists
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS profile;

-- Create profile table
CREATE TABLE profile (
    userid INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(40),
    lastname VARCHAR(40),
    companyname VARCHAR(40),
    profilepictureurl TEXT,
    userrole VARCHAR(40)
);
--Create mock data for user
insert into profile (firstname, lastname, companyname, profilepictureurl, userrole)
values ('Example 1', 'One', 'Company 1', 'img:url', 'Role1'),
       ('Example 2', 'Two', 'Company 2', 'img:url', 'Role2'),
       ('Example 3', 'Three', 'Company 3', 'img:url', 'Role3'),
       ('Example 4', 'Four', 'Company 4', 'img:url', 'Role4');
-- Create post table
CREATE TABLE post (
    postid INT PRIMARY KEY AUTO_INCREMENT,
    userprofileid INT NOT NULL,
    posttext TEXT NOT NULL,
    postimgurl TEXT NOT NULL,
    pubdate TIMESTAMP NOT NULL,
    FOREIGN KEY (userprofileid) REFERENCES profile(userid)
);
-- mock data for post
insert into post (userprofileid, posttext, postimgurl, pubdate)
    values (1, 'Post 1', 'img_url', '1-2-12'),
            (2, 'Post 2', 'img_url', '2-2-12'),
            (3, 'Post 3', 'img_url', '3-2-12'),
            (4, 'Post 4', 'img_url', '4-2-12');

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
