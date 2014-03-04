create table client
(
   id bigint auto_increment,
   name varchar2(255) NOT NULL,
   iban varchar2(255),
   personalnumber varchar2(50),
   country varchar2(50) NOT NULL,
   city varchar2(50) NOT NULL,
   district varchar2(50),
   street varchar2(255),
   streetno varchar2(10),
   miscAddress varchar2(255)
);