CREATE TABLE pollingcomment (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question VARCHAR(255) DEFAULT NULL,
    username VARCHAR(255) DEFAULT NULL,
    comment VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE coursecomment (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    course VARCHAR(255) DEFAULT NULL,
    username VARCHAR(255) DEFAULT NULL,
    comment VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE course (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    coursecode VARCHAR(255) DEFAULT NULL,
    coursename VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (coursecode)
);


CREATE TABLE note (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    filename VARCHAR(255) DEFAULT NULL,
    content_type VARCHAR(255) DEFAULT NULL,
    content BLOB DEFAULT NULL,
    coursecode VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (coursecode) REFERENCES course(coursecode)
);


CREATE TABLE polling (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question VARCHAR(255) DEFAULT NULL,
option1 VARCHAR(255) DEFAULT NULL,
option2 VARCHAR(255) DEFAULT NULL,
option3 VARCHAR(255) DEFAULT NULL,
option4 VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pollingRecord (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(255) DEFAULT NULL,
    question VARCHAR(255) DEFAULT NULL,
    choice VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pollingResult (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question VARCHAR(255) DEFAULT NULL,
    totalchoiceA INT DEFAULT 0,
    totalchoiceB INT DEFAULT 0,
    totalchoiceC INT DEFAULT 0,
    totalchoiceD INT DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE pollingRealTime (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(255) DEFAULT NULL,
    question VARCHAR(255) DEFAULT NULL,
    choice VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);




CREATE TABLE student (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(255) DEFAULT NULL,
    password VARCHAR(255) DEFAULT NULL,
    fullname VARCHAR(255) DEFAULT NULL,
    phonenumber VARCHAR(255) DEFAULT NULL,
    address VARCHAR(255) DEFAULT NULL,
    userrole VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (username)
);


CREATE TABLE voteduser (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question VARCHAR(255) DEFAULT NULL,
    username VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);


INSERT INTO student(username,password,fullname,phonenumber,address,userrole) VALUES ('guest','{noop}guest','guest','guest','guest','ROLE_GUEST' );

INSERT INTO student(username,password,fullname,phonenumber,address,userrole) VALUES ('keith','{noop}keith','keith','keith','keith','ROLE_LECTURER' );

INSERT INTO student(username,password,fullname,phonenumber,address,userrole) VALUES ('gchi','{noop}gchi','gchi','gchi','gchi','ROLE_STUDENT' );