
create database agency;
use agency;

-- tables

-- Table: country-----------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE country (
    id int  NOT NULL auto_increment PRIMARY KEY,
  
    country_name varchar(30)  NOT NULL,
    country_name_ua varchar(30)  NOT NULL
);
 


-- Table: city-----------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE city (
    id int  NOT NULL auto_increment PRIMARY KEY,
    country_id int  NOT NULL,
   
    city_name varchar(30)  NOT NULL,
    city_name_ua varchar(30)  NOT NULL,
    
    CONSTRAINT city_country 
    FOREIGN KEY (country_id)
    REFERENCES country(id)
);



-- Table: user---------------------------------------------------------------------------------------------------------------------------------------------



CREATE TABLE user (
    id int NOT NULL auto_increment PRIMARY KEY,
    login varchar(30)  NOT NULL,
    password varchar(64)  NOT NULL,
    salt varchar(64) NOT NULL,
    role varchar(5) NOT NULL default 'USER',
    firstname varchar(30)  NOT NULL,
    lastname varchar(30)  NOT NULL,
    email varchar(30)  NOT NULL,
    phone_number varchar(13)  NOT NULL,
    isRegular boolean  NOT NULL default false
);



-- Table: tour-----------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE tour (
    id int  NOT NULL auto_increment PRIMARY KEY,
    
    city_from varchar(20) NOT NULL,
	tour_type varchar(10)  NOT NULL,
    transport_type varchar(5)  NOT NULL,
    name varchar(50)  NOT NULL,
    name_ua varchar(50)  NOT NULL,
    description varchar(1300) NOT NULL,
    description_ua varchar(1300) NOT NULL,
    duration int  NOT NULL,
    path_image varchar(50)  NOT NULL

);



-- Table: pass----------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE pass (
    id int  NOT NULL auto_increment PRIMARY KEY,
	tour_id int  NOT NULL,
 
    leaving_date date  NOT NULL,
    quantity_available int NOT NULL,
    price decimal(6,2)  NOT NULL,
    isHot boolean  NOT NULL default false,
    discountForRegular int NOT NULL default 0,
    
    CONSTRAINT pass_tour
    FOREIGN KEY (tour_id)
    REFERENCES tour(id)
);


-- Table: order---------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE orders (
    id int  NOT NULL auto_increment PRIMARY KEY,
    pass_id int NOT NULL,
    user_id int NOT NULL,
    
    quantity int  NOT NULL,
    totalPrice decimal(6,2)  NOT NULL,
    order_date date NOT NULL,
    
    CONSTRAINT order_pass
    FOREIGN KEY (pass_id)
    REFERENCES pass(id),
    
    CONSTRAINT order_user
    FOREIGN KEY (user_id)
    REFERENCES user(id)
);


-- Table: tour_hotel------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE tour_city (
    id int  NOT NULL auto_increment PRIMARY KEY,
    tour_id int  NOT NULL,
    city_id int  NOT NULL, 
   
    CONSTRAINT tour_city_tour
    FOREIGN KEY (tour_id)
    REFERENCES tour (id),
   
    CONSTRAINT tour_city_city
    FOREIGN KEY (city_id)
    REFERENCES city (id)
);

