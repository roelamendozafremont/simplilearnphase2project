CREATE TABLE `payments` (
  `bill_date` date NOT NULL,
  `bfirst_name` char(20) DEFAULT NULL,
  `bmiddle_name` char(20) DEFAULT NULL,
  `blast_name` char(20) DEFAULT NULL,
  `card_number` char(20) NOT NULL,
  `card_expiration` char(20) DEFAULT NULL,
  `bstreet` char(20) DEFAULT NULL,
  `bcity` char(20) DEFAULT NULL,
  `bstate` char(20) DEFAULT NULL,
  `bcountry` char(20) DEFAULT NULL,
  `bzipcode` char(20) DEFAULT NULL,
  `bill_amount` float(6,2) DEFAULT NULL,
  `book_number` char(20) NOT NULL,
  PRIMARY KEY (`bill_date`,`card_number`,`book_number`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii