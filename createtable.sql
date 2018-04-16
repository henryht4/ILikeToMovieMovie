CREATE DATABASE IF NOT EXISTS moviedb;

USE moviedb;

CREATE TABLE IF NOT EXISTS movies(
	id varchar(10) NOT NULL, 
	title varchar(100) NOT NULL, 
	year int NOT NULL, 
	director varchar(100) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS stars(
	id varchar(10) NOT NULL,
    name varchar(100) NOT NULL,
    birthYear int,
    PRIMARY KEY(id)
    );

CREATE TABLE IF NOT EXISTS stars_in_movies(
	starID varchar(10) NOT NULL,
    movieID varchar(10) NOT NULL,
	FOREIGN KEY(starID) REFERENCES stars(id),
    FOREIGN KEY(movieID) REFERENCES movies(id)
    );

CREATE TABLE IF NOT EXISTS genres(
	id int NOT NULL AUTO_INCREMENT,
    name varchar(32) NOT NULL,
    PRIMARY KEY(id)
    );
    
CREATE TABLE IF NOT EXISTS genres_in_movies(
	genreId int NOT NULL,
    movieID varchar(10) NOT NULL,
    FOREIGN KEY(genreID) REFERENCES genres(id),
    FOREIGN KEY(movieID) REFERENCES movies(id)
    );

    
CREATE TABLE IF NOT EXISTS creditcards(
	id varchar(20) NOT NULL,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    expiration date NOT NULL,
    PRIMARY KEY (id)
    );
    
    
CREATE TABLE IF NOT EXISTS customers(
	id int NOT NULL AUTO_INCREMENT,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    ccID varchar(20) NOT NULL,
    address varchar(200) NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(20) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(ccID) REFERENCES creditcards(id)
    );

CREATE TABLE IF NOT EXISTS sales(
	id int NOT NULL AUTO_INCREMENT,
    customerID int NOT NULL,
    moviesID varchar(10) NOT NULL,
    saleDate date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(customerID) REFERENCES customers(id),
	FOREIGN KEY(moviesID) REFERENCES movies(id)
    );

CREATE TABLE IF NOT EXISTS ratings(
	movieID varchar(10) NOT NULL,
    rating float NOT NULL,
    numVotes int NOT NULL,
	FOREIGN KEY(movieID) REFERENCES movies(id)
    );
    


