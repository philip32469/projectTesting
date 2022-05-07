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


INSERT INTO student(username,password,fullname,phonenumber,address,userrole) VALUES ('guest','{noop}guest','guest','guest','guest','ROLE_GUEST' );