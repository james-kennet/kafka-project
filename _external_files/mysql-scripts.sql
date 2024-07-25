CREATE TABLE address_transaction (
id int NOT NULL AUTO_INCREMENT,
nric varchar(30),
status varchar(30),
unitNo varchar(30),
streetName  varchar(100),
country varchar(30),
postalCode varchar(10),
clientType varchar(20),
PRIMARY KEY (id)
);

CREATE TABLE address_salesforce (
id int NOT NULL AUTO_INCREMENT,
nric varchar(30),
unitNo varchar(30),
streetName  varchar(100),
country varchar(30),
postalCode varchar(10),
deleted bit not null default 0,
PRIMARY KEY (id)
);

CREATE TABLE address_website (
id int NOT NULL AUTO_INCREMENT,
nric varchar(30),
unitNo varchar(30),
streetName  varchar(100),
country varchar(30),
postalCode varchar(10),
deleted bit not null default 0,
PRIMARY KEY (id)
);

CREATE TABLE address_life400 (
id int NOT NULL AUTO_INCREMENT,
nric varchar(30),
unitNo varchar(30),
streetName  varchar(100),
country varchar(30),
postalCode varchar(10),
deleted bit not null default 0,
PRIMARY KEY (id)
);