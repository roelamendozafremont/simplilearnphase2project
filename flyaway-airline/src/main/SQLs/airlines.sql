CREATE TABLE `airlines` (
  `code` char(20) NOT NULL,
  `name` char(30) NOT NULL,
  `plane_type` char(20) NOT NULL,
  `seat_capacity` int DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii