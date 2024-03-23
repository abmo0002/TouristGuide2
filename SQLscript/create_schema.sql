CREATE SCHEMA tourist_guide;


CREATE TABLE tag(
tagID INT auto_increment,
tagName VARCHAR(50) NOT NULL unique,
primary key(tagID)
);


CREATE TABLE tourist_attraction_tag(
touristID INT,
tagID INT,
foreign key (touristID) references tourist_attraction(touristID),
FOREIGN KEY (tagID) references tag(tagID)
);

CREATE TABLE city(
cityID INT auto_increment,
cityName VARCHAR(50) NOT NULL unique,
primary key(cityID)
);

CREATE TABLE tourist_attraction(
touristID INT auto_increment,
name VARCHAR(50) NOT NULL unique,
description VARCHAR(255) NOT NULL,
cityID INT,
primary key(touristID),
foreign key(cityID) references city(cityID)
);