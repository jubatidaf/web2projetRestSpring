 CREATE DATABASE sepatransactiondb;
 
 USE sepatransactiondb;
 
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET GLOBAL time_zone = '+1:00';

CREATE TABLE `sepatransactiontable` (
  `id_sepa_transaction` int(20) NOT NULL,
  `numero` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `paieid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `instdamt` double NOT NULL,
  `mandatid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `datetrans` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `bic` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `iban` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `relateinf` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `sepatransactiontable` (`id_sepa_transaction`, `numero`, `paieid`, `instdamt`, `mandatid`, `datetrans`, `bic`, `nom`, `iban`, `relateinf`) VALUES
(1, 'TJ0001', 'REF OPE IIII', 800, 'MANDAT NO 141414', '2004-01-01', 'ABNAFRPP', 'debiteur 1', 'FR7630001007941678901852345', 'facteur 1'),
(2, 'TJ0002', 'REF OPE AAAA', 950, 'MANDAT NO 178112', '2007-12-03', 'AECFFR21', 'debiteur 2', 'FR7630004000031678901432345', 'facteur 2'),
(3, 'TJ0003', 'REF OPE CCCC', 550, 'MANDAT NO 142113', '2008-09-18', 'AFRIFRPP', 'debiteur 3', 'FR7630006000011234567890189', 'facteur 3'),
(4, 'TJ0004', 'REF OPE DDDD', 500, 'MANDAT NO 222114', '2006-06-10', 'AGFBFRCC', 'debiteur 4', 'FR7610107001078901291123456', 'facteur 4'),
(5, 'TJ0005', 'REF OPE LLLL', 8000.98, 'MANDAT NO 789115', '2009-08-07', 'AGRIFRPI', 'debiteur 5', 'FR7611315000456789013801123', 'facteur 5'),
(6, 'TJ0006', 'REF OPE FFFF', 705.89, 'MANDAT NO 121116', '2011-11-10', 'AGRIFRPP', 'debiteur 6', 'FR7630002032536789016812345', 'facteur 6'),
(7, 'TJ0007', 'REF OPE EEEE', 789.89, 'MANDAT NO 112117', '2012-08-22', 'AGRIMQMX', 'debiteur 7', 'FR7630056009271678901822345', 'facteur 7'),
(8, 'TJ0008', 'REF OPE GGGG', 111.04, 'MANDAT NO 112118', '2012-01-04', 'AGRIRERX', 'debiteur 8', 'FR7611808009101789014723456', 'facteur 8');


ALTER TABLE `sepatransactiontable`
  ADD PRIMARY KEY (`id_sepa_transaction`);

ALTER TABLE `sepatransactiontable`
  MODIFY `id_sepa_transaction` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
