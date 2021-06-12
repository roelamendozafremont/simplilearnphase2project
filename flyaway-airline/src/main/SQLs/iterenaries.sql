CREATE TABLE `iterenaries` (
  `flight_date` date NOT NULL,
  `origin` char(20) NOT NULL,
  `destination` char(20) NOT NULL,
  `seats_available` int DEFAULT NULL,
  `flight_code` char(20) DEFAULT NULL,
  `fare` float(7,2) DEFAULT NULL,
  PRIMARY KEY (`flight_date`,`origin`,`destination`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii