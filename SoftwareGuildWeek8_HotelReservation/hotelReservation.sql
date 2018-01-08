<<<<<<< HEAD
CREATE
DATABASE HotelReservation;

DROP DATABASE IF EXISTS HotelReservation;

=======
DROP DATABASE IF EXISTS HotelReservation;

CREATE
DATABASE HotelReservation;

>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
USE HotelReservation;

CREATE TABLE Amenity (
AmenityID  INT NOT NULL AUTO_INCREMENT,
AmenityName VARCHAR(40) NOT NULL,
PRIMARY KEY(AmenityID)
);

CREATE TABLE RoomInfo(
RoomNo INT NOT NULL,
<<<<<<< HEAD
RoomType VARCHAR(40) NOT NULL,
Occupancy INT NOT NULL,
PRIMARY KEY(RoomNo)
);

=======
RoomPriceid INT NOT NULL,
Occupancy INT NOT NULL,
PRIMARY KEY(RoomNo)
);
 	 	 	
>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
CREATE TABLE RoomAmenity (
RoomNo INT NOT NULL,
AmenityID INT NOT NULL,
PRIMARY KEY(AmenityID, RoomNo)
);
<<<<<<< HEAD

=======
 	 	 		
>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
CREATE TABLE RoomRes (
RoomNo INT NOT NULL,
ReservationNo INT NOT NULL,
PRIMARY KEY(RoomNo, ReservationNo)
);
<<<<<<< HEAD

=======
 
>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
CREATE TABLE Customer (
Phone INT NOT NULL,
CustomerName VARCHAR(40) NOT NULL,
Email VARCHAR(40) NOT NULL,
PRIMARY KEY(Phone)
);
<<<<<<< HEAD

=======
 	 	
>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
CREATE TABLE Reservation (
ReservationNo INT NOT NULL,
Phone INT NOT NULL,
PRIMARY KEY(ReservationNo)
);
<<<<<<< HEAD

CREATE TABLE Guest(
=======
 	 	 
 CREATE TABLE Guest(
>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
GuestID INT NOT NULL,
ReservationNo INT NOT NULL,
GuestName VARCHAR(40) NOT NULL,
GuestAge INT NOT NULL,
PRIMARY KEY(GuestID)
);
<<<<<<< HEAD

CREATE TABLE AddOn(
TimeDetail VARCHAR(40) NOT NULL,
AddOnName VARCHAR(40) NOT NULL,
AddOnPrice INT NOT NULL,
PRIMARY KEY(TimeDetail,AddOnName )
);

CREATE TABLE RoomPrice(
TimeDetail VARCHAR(40) NOT NULL,
RoomType VARCHAR(40) NOT NULL,
Price INT NOT NULL,
PRIMARY KEY(TimeDetail,RoomType )
=======
 	 	 
CREATE TABLE AddOn(
AddOnid INT NOT NULL,
TimeDetail VARCHAR(40) NOT NULL,
AddOnName VARCHAR(40) NOT NULL,
AddOnPrice INT NOT NULL,
PRIMARY KEY(AddOnid )
);
 	 	 	
CREATE TABLE RoomPrice(
RoomPriceid INT NOT NULL,
TimeDetail VARCHAR(40) NOT NULL,
RoomType VARCHAR(40) NOT NULL,
Price INT NOT NULL,
PRIMARY KEY(RoomPriceid)
>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
);

CREATE TABLE PromotionCode(
PromotionCodeID VARCHAR(20) NOT NULL,
StartDate VARCHAR(20) NOT NULL,
EndDate VARCHAR(20) NOT NULL,
DiscountAmt INT NOT NULL,
DiscountRate INT NOT NULL,
PRIMARY KEY(PromotionCodeID)
);
<<<<<<< HEAD

CREATE TABLE AddOnBill(
BillingID INT NOT NULL,
AddOnName VARCHAR(40) NOT NULL,
PRIMARY KEY(BillingID, AddOnName)
=======
 	 	
CREATE TABLE AddOnBill(
AddOnBillid INT NOT NULL,
BillingID INT NOT NULL,
AddOnid INT NOT NULL,
PRIMARY KEY(AddOnBillid)
>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
);

CREATE TABLE Billing(
BillingID INT NOT NULL,
ReservationNo INT NOT NULL,
StartDate VARCHAR(20) NOT NULL,
EndDate VARCHAR(20) NOT NULL,
RoomTotalPrice INT NOT NULL,
AddOnTotalPrice INT NOT NULL,
PromotionCodeID VARCHAR(20) NOT NULL,
Tax INT NOT NULL,
TotalPrice INT NOT NULL,
PRIMARY KEY(BillingID)
);

ALTER TABLE RoomInfo
<<<<<<< HEAD
ADD FOREIGN KEY (RoomType) REFERENCES RoomPrice(TimeDetail,RoomType);

ALTER TABLE RoomAmenity
ADD FOREIGN KEY (AmenityID) REFERENCES Amenity(AmenityID),
ADD FOREIGN KEY (RoomNo) REFERENCES RoomInfo(RoomNo);

=======
ADD FOREIGN KEY (RoomPriceid) REFERENCES RoomPrice(RoomPriceid);
 	 	
ALTER TABLE RoomAmenity
ADD FOREIGN KEY (AmenityID) REFERENCES Amenity(AmenityID),
ADD FOREIGN KEY (RoomNo) REFERENCES RoomInfo(RoomNo);
 	 	 	
>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
ALTER TABLE RoomRes
ADD FOREIGN KEY (RoomNo) REFERENCES RoomInfo(RoomNo),
ADD FOREIGN KEY (ReservationNo) REFERENCES Reservation(ReservationNo);

ALTER TABLE Reservation
ADD FOREIGN KEY (Phone) REFERENCES Customer(Phone);
<<<<<<< HEAD

ALTER TABLE Guest
ADD FOREIGN KEY (ReservationNo) REFERENCES Reservation(ReservationNo);

ALTER TABLE AddOnBill
ADD FOREIGN KEY (BillingID) REFERENCES Billing(BillingID),
ADD FOREIGN KEY (AddOnName) REFERENCES AddOn(AddOnName);

=======
 	 	 	
ALTER TABLE Guest
ADD FOREIGN KEY (ReservationNo) REFERENCES Reservation(ReservationNo);
 	 
ALTER TABLE AddOnBill
ADD FOREIGN KEY (BillingID) REFERENCES Billing(BillingID),
ADD FOREIGN KEY (AddOnid) REFERENCES AddOn(AddOnid);
 	 	 	
>>>>>>> 7b0bb8c0c77725abb41de15aefe59bc41679f20c
ALTER TABLE Billing
ADD FOREIGN KEY (PromotionCodeID) REFERENCES PromotionCode(PromotionCodeID);