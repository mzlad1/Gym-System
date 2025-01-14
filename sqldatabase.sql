
create database Gym;
use Gym;

CREATE TABLE Employees(
	EID Varchar(32) PRIMARY KEY
);



CREATE TABLE Coaches(
	CID Varchar(32) PRIMARY KEY UNIQUE,
	coachName Varchar(32) ,
	phoneNumber Varchar(32),
    FOREIGN KEY (CID) REFERENCES Employees(EID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Nutritionists(
	NID Varchar(32) PRIMARY KEY UNIQUE,
    nutritionistName varchar(32),
    phoneNumber varchar(32),
    officeAddress varchar(32),
    FOREIGN KEY (NID) REFERENCES Employees(EID) ON DELETE CASCADE ON UPDATE CASCADE
);

create table WorkOutPlan(
WID varchar(32) primary key ,
Plantype varchar(32) ,
DmPercentage varchar(32) ,
dfPercentage varchar(32) , 
Deadline varchar(32)  ,
exercises varchar(255) 


);

create table FoodPlan(
PID varchar(32) primary key ,
Plantype varchar(32) ,
DmPercentage varchar(32) ,
dfPercentage varchar(32) , 
Deadline varchar(32)  ,
meals varchar(255) 


);



create table Members(
	GID varchar(32) primary key,
	Name varchar(32),
	age int,
	weight real ,
	height real,
	addrees varchar(32) ,
	phoneNumber varchar(32) ,
	lockerOp boolean,
	EndDate varchar(32),
    CID VarChar(32),
    NID VarChar(32),
    PID VarChar(32),
    WID varchar(32),
    FOREIGN KEY (CID) REFERENCES Coaches(CID) ON UPDATE CASCADE,
	FOREIGN KEY (NID) REFERENCES Nutritionists(NID) ON UPDATE CASCADE,
    FOREIGN KEY (PID) REFERENCES FoodPlan(PID) ON UPDATE CASCADE ,
    FOREIGN KEY (WID) REFERENCES WorkOutPlan(WID) ON UPDATE CASCADE
);

create table Payment(
PTID int Not null auto_increment,
amount real,
dateSale varchar(32),
GID varchar(32) ,
primary key(PTID),
FOREIGN KEY (GID) REFERENCES Members(GID) ON UPDATE CASCADE

);





CREATE TABLE Bio(
	BID varchar(32) primary key,
	WorkingDays varchar (32),
	WorkingTimes varchar (32),
	TrainingType varchar (32),
    EID VarChar(32),
    FOREIGN KEY (EID) REFERENCES Employees(EID) on delete cascade
);


create table Items(
IID varchar(32) primary key ,
name varchar(32) ,
price real,
quantity int ,
proft real ,
purchase real ,
total real

);
create table login(
LID int Not null auto_increment primary key ,
username varchar(128) ,
Password varchar(128) ,
AdminType varchar(32),
 GID varchar(32) default NULL,
 EID varchar(32) default NULL,
 foreign key (GID) REFERENCES Members(GID) ON UPDATE CASCADE ON DELETE cascade,
 foreign key (EID) REFERENCES Employees(EID) ON UPDATE CASCADE ON DELETE cascade 
);
