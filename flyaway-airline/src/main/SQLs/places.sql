CREATE TABLE `places` (
  `code` char(20) NOT NULL,
  `street` char(30) NOT NULL,
  `city` char(20) NOT NULL,
  `state` char(20) NOT NULL,
  `country` char(20) NOT NULL,
  `zipcode` char(20) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii