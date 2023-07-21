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
    values (1, 'Post 1', 'img_url', '2000-01-01 00:00:01.0'),
            (2, 'Post 2', 'img_url', '2000-01-01 00:00:02.0'),
            (3, 'Post 3', 'img_url', '2000-01-01 00:00:03.0'),
            (4, 'Post 4', 'img_url', '2000-01-01 00:00:04.0'),
            (1, 'Post 5', 'img_url', '2000-01-01 00:00:05.0'),
            (1, 'Post 6', 'img_url', '2000-01-01 00:00:06.0');

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

insert into comments(commenttext, pubdate, postid, autorid)
    values ('Comment 1', '2000-01-01 00:00:01', 1, 1 ),
           ('Comment 2', '2000-02-02 00:00:02', 2, 2 ),
           ('Comment 3', '2000-03-03 00:00:03', 3, 3 ),
           ('Comment 4', '2000-01-01 00:00:04', 3, 3 ),
           ('Comment 5', '2000-01-01 00:00:05', 3, 2 ),
           ('Comment 1 - post 4', '2000-01-01 00:00:05', 4, 3 ),
           ('Comment 2 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 3 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 4 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 5 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 6 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 7 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 8 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 9 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 10 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 11 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 12 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 13 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 14 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 15 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 16 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 17 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 18 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 19 - post 4', '2000-01-01 00:00:05', 4, 1 ),
           ('Comment 20 - post 4', '2000-01-01 00:00:05', 4, 1 );

