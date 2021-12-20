DROP DATABASE IF EXISTS luming;
CREATE DATABASE luming;
use luming;
DROP TABLE IF EXISTS booklist;
DROP TABLE IF EXISTS noincludebook;


create table booklist
(
    id int not null primary key auto_increment,
    bookid int not null primary key,
    bookname varchar(20) not null,
    link  varchar(45) not null,
    author varchar(20) not null,
    abstract varchar(45),
    episodenumber int not null
)Engine=InnoDB default charset utf8;


create table noincludebook
(
    id int not null primary key auto_increment,
    bookname varchar(20) not null,
    author varchar(20) not null
)Engine=InnoDB default charset utf8;