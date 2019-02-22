create database if not exists games_collection;

use games_collection;

drop table if exists user_game;
drop table if exists game;
drop table if exists publisher;
drop table if exists app_user;

create table app_user
(
	user_id int(6) auto_increment,
    username varchar(50) not null unique,
    email varchar(50) not null unique,
    
    constraint user_id_pk primary key(user_id)
);

create table publisher
( 
	publisher_id int(4) auto_increment,
    publisher_name varchar(50) unique not null,
    
    constraint publisher_id_pk primary key(publisher_id)
);

create table game
(
	game_id int(10) auto_increment,
    title varchar(100) not null,
    year_of_publication date not null,
    publisher_id int(4) not null,
    
    constraint game_id_pk primary key(game_id),
    constraint publisher_id_game_fk foreign key(publisher_id) references publisher(publisher_id)
);

create table user_game
(
	user_id int(6) not null,
    game_id int(10) not null,
    
    constraint user_game_pk primary key(user_id, game_id),
    constraint user_id_user_game_fk foreign key(user_id) references app_user(user_id),
    constraint game_id_user_game_fk foreign key(game_id) references game(game_id)
);