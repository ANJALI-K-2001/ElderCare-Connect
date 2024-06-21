/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - eldercareconnect
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eldercareconnect` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `eldercareconnect`;

/*Table structure for table `appointment` */

DROP TABLE IF EXISTS `appointment`;

CREATE TABLE `appointment` (
  `appointment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `reason` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `amount` int(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `appointment` */

insert  into `appointment`(`appointment_id`,`user_id`,`doctor_id`,`reason`,`date`,`time`,`amount`,`status`) values 
(1,1,1,'fever','30/07/2024','9:00',5000,'paid'),
(2,1,1,'ghj','15/4/2024','2024-04-15 14:38:42',500,'pending'),
(3,1,2,'nil','15/4/2024','2024-04-15 15:23:50',500,'pending'),
(4,2,2,'nil','15/4/2024','2024-04-15 15:24:31',500,'pending'),
(5,3,3,'Nill','15/4/2024','2024-04-15 15:34:35',500,'PAID');

/*Table structure for table `caretaker` */

DROP TABLE IF EXISTS `caretaker`;

CREATE TABLE `caretaker` (
  `caretaker_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `full_name` varchar(45) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`caretaker_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `caretaker` */

insert  into `caretaker`(`caretaker_id`,`login_id`,`full_name`,`place`,`phone`,`email`) values 
(1,1,'xxx','tcr','45565','afssdg'),
(2,35,'gdhffiv','ccjcjg','55867789','anjalirajeevnair@gmail.com'),
(3,36,'gdhffiv','ccjcjg','55867789','anjalirajeevnair@gmail.com'),
(4,37,'gdhffiv','ccjcjg','55867789','anjalirajeevnair@gmail.com'),
(5,38,'gdhffiv','ccjcjg','55867789','anjalirajeevnair@gmail.com'),
(6,39,'gdhffiv','ccjcjg','55867789','anjalirajeevnair@gmail.com'),
(7,40,'anjali','cherpu','8289393939','anjalirajeevnair@gmail.com');

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `sender_type` varchar(100) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `receiver_type` varchar(100) DEFAULT NULL,
  `message` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`chat_id`,`sender_id`,`sender_type`,`receiver_id`,`receiver_type`,`message`,`date`,`time`) values 
(1,18,'homecare',4,'user','hloo','2024-04-13','14:39:37'),
(2,18,'homecare',4,'user','hiii','2024-04-13','14:39:49'),
(3,18,'homecare',1,'caretaker','hiii','2024-04-13','14:55:12'),
(4,18,'homecare',1,'caretaker','hloo','2024-04-13','14:55:22'),
(5,40,'caretaker',1,'homecare','hii','2024-04-13','15:08:56'),
(6,40,'caretaker',48,'homecare','hii','2024-04-13','15:10:54'),
(7,18,'homecare',40,'caretaker','hlooo','2024-04-13','15:11:45'),
(8,40,'caretaker',18,'homecare','hii','2024-04-13','15:12:46'),
(9,51,'user',18,'homecare','hii','2024-04-13','15:28:50'),
(10,51,'user',18,'homecare','heyy','2024-04-13','15:29:49'),
(11,51,'user',18,'homecare','heyyy','2024-04-13','15:31:13'),
(12,40,'caretaker',44,'homecare','hi','2024-04-13','16:43:44'),
(13,51,'user',1,'homecare','hi','2024-04-15','18:55:48'),
(14,51,'user',44,'homecare','hi','2024-04-15','18:56:06'),
(15,51,'user',1,'homecare','hi','2024-04-15','19:26:14'),
(16,18,'homecare',4,'user','hi','2024-04-16','11:17:24'),
(17,18,'homecare',4,'user','hi','2024-04-16','11:31:44'),
(18,18,'homecare',1,'caretaker','hi','2024-04-16','11:31:53'),
(19,18,'homecare',4,'user','jii','2024-04-16','11:45:07'),
(20,18,'homecare',67,'user','hiewujrgkl','2024-04-16','11:45:13'),
(21,18,'homecare',51,'user','hettt sdgyufihgerjothkl','2024-04-16','11:45:23');

/*Table structure for table `complaints` */

DROP TABLE IF EXISTS `complaints`;

CREATE TABLE `complaints` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `reply` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `complaints` */

insert  into `complaints`(`complaint_id`,`sender_id`,`title`,`description`,`reply`,`date`) values 
(1,1,'sdsfjd','dsjo','hlo','10/10/2022'),
(2,4,'sdsffdff','ssfsd','jiii','2024-03-22'),
(3,4,'sfsf','gdg','hii','2024-03-22'),
(4,3,'sfsf','gdg','pending','2024-03-23'),
(5,3,'sfsf','','heiii','2024-03-23'),
(6,3,'kh','nn','pending','2024-03-23'),
(7,3,'aa','aa','pending','2024-03-23'),
(8,3,'sfsf','aa','pending','2024-03-23'),
(9,16,'hlo','hii','okkk','2024-03-23'),
(10,18,'heiiii','hloooo','accepted','2024-03-25'),
(11,6,'Animi voluptatem au','Consequat Et labore','pending','2024-04-11'),
(12,21,'Non enim aperiam eos','Debitis veritatis al','pending','2024-04-11'),
(13,18,'Et et do omnis delen','Modi sit inventore u','pending','2024-04-11'),
(14,16,'Voluptatibus ut esse','Nostrud fugit delec','pending','2024-04-12'),
(15,51,'null','androidx.appcompat.widget.AppCompatEditText','pending','2024-04-15'),
(16,51,'nffjj','androidx.appcompat.widget.AppCompatEditText','pending','2024-04-15'),
(17,51,'urdu hh','uhhg','pending','2024-04-15'),
(18,21,'Culpa dolorem ad te','Quod esse impedit b','pending','2024-04-15'),
(19,21,'Aliquam velit vitae','Et excepteur cillum ','pending','2024-04-15'),
(20,16,'Distinctio Eos libe','Totam commodo est n','pending','2024-04-16'),
(21,18,'Quidem quas quis eni','Corporis aut sint r','pending','2024-04-16');

/*Table structure for table `doctor` */

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `doctor_name` varchar(45) DEFAULT NULL,
  `qualification` varchar(45) DEFAULT NULL,
  `specialised_in` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `license` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `doctor` */

insert  into `doctor`(`doctor_id`,`login_id`,`doctor_name`,`qualification`,`specialised_in`,`phone`,`email`,`license`) values 
(1,21,'abhi','MBBS','DERMATOLOGY','8547418286','abhi@gmail.com','1001'),
(2,110,'anjal','mbbs','dermatology','08547418286','anjalirajeevnair@gmail.com','hhk'),
(3,12,'anjali k','mbbs','dermatology','08547418286','anjalirajeevnair@gmail.com','hhk'),
(4,13,'anjali k','mbbs','dermatology','08547418286','anjalirajeevnair@gmail.com','hhk'),
(5,14,'anjali k','mbbs','dermatology','08547418286','anjalirajeevnair@gmail.com','hhk'),
(6,21,'anjal','mbbs','dermatology','08547418286','anjalirajeevnair@gmail.com','asdasfjo'),
(7,22,'anjal','mbbs','dermatology','08547418286','anjalirajeevnair@gmail.com','asdasfjo'),
(8,23,'abhi','mbbs','dermatology','08547418286','abhiramrajeevnair@gmail.com','asdasfjo'),
(9,41,'Herman Frye','Commodo tempore aut','Rerum est recusandae','+1 (917) 928-1185','meliq@mailinator.com','A et quas excepteur '),
(10,42,'Felicia Compton','Ipsum placeat place','Veniam obcaecati eu','+1 (394) 614-4586','wobaryjura@mailinator.com','Quod error laboris m'),
(11,43,'Dean Roberson','Quam ut sit nemo exc','Possimus lorem nesc','+1 (699) 236-3692','hupy@mailinator.com','Elit minus rerum au'),
(12,45,'Morgan Bullock','At dolore earum eius','Eu Nam voluptas aliq','+1 (812) 421-6543','jibuqale@mailinator.com','Dolore duis sit esse'),
(13,46,'Cole Evans','Fugiat omnis volupt','Minus Nam dolorum cu','+1 (365) 704-6384','kixegir@mailinator.com','Sapiente temporibus ');

/*Table structure for table `emergency_alert` */

DROP TABLE IF EXISTS `emergency_alert`;

CREATE TABLE `emergency_alert` (
  `emergency_alert_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`emergency_alert_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `emergency_alert` */

insert  into `emergency_alert`(`emergency_alert_id`,`user_id`,`title`,`date`,`time`,`status`) values 
(1,1,'nil','13/04/2024','5:00','update'),
(2,2,'fgmh','dbhng','dfgh','dgf'),
(3,3,'edfghjk','efgh','fghjfg','update'),
(4,51,'speef','2024-04-15','2024-04-15 20:14:31','pending');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` varchar(45) DEFAULT NULL,
  `feedback` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`sender_id`,`receiver_id`,`feedback`,`date`) values 
(1,1,'2','good','1/2/2001'),
(2,51,'1','okk','2024-04-15'),
(3,51,'1','okkkk','2024-04-15');

/*Table structure for table `homecare` */

DROP TABLE IF EXISTS `homecare`;

CREATE TABLE `homecare` (
  `homecare_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `image` varchar(5000) DEFAULT NULL,
  `idproof` varchar(5000) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`homecare_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `homecare` */

insert  into `homecare`(`homecare_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`,`image`,`idproof`,`gender`) values 
(1,18,'anjali','rajeev','tcr','8547418286','sfdsf','fsf','sfff','fsdf'),
(2,1,'anjali ','the great','cherpu','08547418286','anjalthegreat@gmail.com','static/f8c89f9c-20a8-4763-be11-5f45c0e68007registration  page.png','static/821a99b4-6220-45ba-a263-6bf6c2a0697fadmin assgin vehicle.png','female'),
(3,44,'Cody Monroe','Kasimir Gaines','Omnis sit ad iure o','+1 (709) 292-9174','vehyxipagu@mailinator.com','static/15bae51f-cc94-4b8d-8929-5505b069178aadmin home page.png','static/0f9d9a7a-7ef4-4434-850d-fb07b2fbb577admin manage emergency parking.png','Sapiente excepturi d'),
(4,48,'Aimee Morales','Sierra Travis','Architecto ut except','+1 (597) 139-2817','guducysix@mailinator.com','static/15bae51f-cc94-4b8d-8929-5505b069178aadmin home page.png','static/0f9d9a7a-7ef4-4434-850d-fb07b2fbb577admin manage emergency parking.png','Ullam id odio deseru');

/*Table structure for table `homecare_request` */

DROP TABLE IF EXISTS `homecare_request`;

CREATE TABLE `homecare_request` (
  `homecare_request_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `homecare_id` int(11) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`homecare_request_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `homecare_request` */

insert  into `homecare_request`(`homecare_request_id`,`user_id`,`homecare_id`,`description`,`date`,`status`) values 
(1,1,1,'kshshf','30/07/2001','ACCEPT'),
(2,3,1,'okk','2024-04-15','Reject'),
(3,3,1,'okkkgh','2024-04-15','ACCEPT');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `usertype` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(16,'car','car','transportation'),
(17,'cars','cars','transportation'),
(18,'homecare','homecare','homecare'),
(21,'doctor','doctor','doctor'),
(20,'admin','123','transportation'),
(19,'car1','car2','transportation'),
(13,'anjalik','anjalik','doctor'),
(22,'doctor1','doctor1','doctor pending'),
(23,'abhi','abhi','doctor pending'),
(24,'ajah','hdggd','caretaker'),
(25,'ajah','hdggd','caretaker'),
(26,'aiyyxgi','jgxxig','caretaker'),
(27,'aiyyxgi','jgxxig','caretaker'),
(28,'aiyyxgi','jgxxig','caretaker'),
(29,'aiyyxgi','jgxxig','caretaker'),
(30,'aiyyxgi','jgxxig','caretaker'),
(31,'aiyyxgi','jgxxig','caretaker'),
(32,'aiyyxgi','jgxxig','caretaker'),
(33,'aiyyxgi','jgxxig','caretaker'),
(34,'aiyyxgi','jgxxig','caretaker'),
(35,'dbkkkk','dhrigigi','caretaker'),
(36,'dbkkkk','dhrigigi','caretaker'),
(37,'dbkkkk','dhrigigi','caretaker'),
(38,'anj','anj','caretaker'),
(39,'anj','anj','caretaker'),
(40,'anu','123','caretaker'),
(41,'posatuqoki','Pa$$w0rd!','pending'),
(42,'tycecej','Pa$$w0rd!','pending'),
(43,'dasafobuw','Pa$$w0rd!','doctor'),
(44,'nawusefy','Pa$$w0rd!','homecare'),
(45,'lefofe','Pa$$w0rd!','doctor'),
(46,'lylelu','Pa$$w0rd!','doctor'),
(47,'sufodimi','Pa$$w0rd!','homecare'),
(48,'sufodimi','Pa$$w0rd!','homecare'),
(49,'wivasedute','Pa$$w0rd!','transportation'),
(50,'wivasedute','Pa$$w0rd!','transportation'),
(51,'aaa','123','user'),
(52,'amal','123','user'),
(53,'amal','123','user'),
(54,'amal','123','user'),
(55,'amal','123','user'),
(56,'amal','123','user'),
(57,'amal','123','user'),
(58,'amal','123','user'),
(59,'amal','123','user'),
(60,'amal','123','user'),
(61,'amal','123','user'),
(62,'amal','123','user'),
(63,'amal','123','user'),
(64,'amal','123','user'),
(65,'amal','123','user'),
(66,'amal','123','user'),
(67,'amal','123','user'),
(68,'ansnns','13467','user');

/*Table structure for table `medicine` */

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `medicine_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `image` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`medicine_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `medicine` */

insert  into `medicine`(`medicine_id`,`user_id`,`name`,`image`) values 
(1,1,'new','static/medicinedetailsimages/db78abba-9b17-4c94-b0e4-f0e8c33dbc81abc.jpg'),
(2,1,'Today','static/medicinedetailsimages/aa9a76fb-a2da-4cb1-8928-7e0c163cbbd9abc.jpg'),
(3,3,'Nothing','static/medicinedetailsimages/387d6d99-73ee-44cf-96a4-4130d3e921deabc.jpg');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `appointment_id` int(11) DEFAULT NULL,
  `amount` int(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`appointment_id`,`amount`,`date`,`status`) values 
(1,5,500,'2024-04-15','PAID');

/*Table structure for table `prescription` */

DROP TABLE IF EXISTS `prescription`;

CREATE TABLE `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `appointment_id` int(11) DEFAULT NULL,
  `prescription_details` varchar(45) DEFAULT NULL,
  `pre_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`prescription_id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

/*Data for the table `prescription` */

insert  into `prescription`(`prescription_id`,`appointment_id`,`prescription_details`,`pre_date`) values 
(34,1,'heii','2024-04-02'),
(33,1,'paracetamol','2024-04-01'),
(32,1,'dosjdos','2024-04-01'),
(31,1,'m','2024-03-22'),
(30,1,'hloo','2024-03-22'),
(29,1,'hloo','2024-03-22'),
(28,1,'good','2024-03-22'),
(27,1,'good','2024-03-22'),
(26,1,'hloo','2024-03-22'),
(25,1,'hloo','2024-03-22'),
(24,1,'hlo','2024-03-22'),
(23,1,'hlo','2024-03-22'),
(22,1,'good','2024-03-22'),
(21,1,'good','2024-03-22'),
(20,1,'jhi','2024-03-22'),
(35,5,'MGF','2024-04-15');

/*Table structure for table `set_alert` */

DROP TABLE IF EXISTS `set_alert`;

CREATE TABLE `set_alert` (
  `alert_id` int(10) NOT NULL AUTO_INCREMENT,
  `medicine_id` int(10) DEFAULT NULL,
  `start_date` varchar(50) DEFAULT NULL,
  `end_date` varchar(50) DEFAULT NULL,
  `time_mrg` varchar(50) DEFAULT NULL,
  `time_aftn` varchar(50) DEFAULT NULL,
  `time_nyt` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`alert_id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `set_alert` */

insert  into `set_alert`(`alert_id`,`medicine_id`,`start_date`,`end_date`,`time_mrg`,`time_aftn`,`time_nyt`,`status`) values 
(1,1,'13/04/2024','13/04/2024','12:58','19:58','12:59','alert_set'),
(2,1,'14/04/2024','14/04/2024','12:58','19:58','12:59','alert_set'),
(3,1,'15/04/2024','15/04/2024','12:58','19:58','12:59','alert_set'),
(4,1,'16/04/2024','16/04/2024','12:58','19:58','12:59','alert_set'),
(5,1,'17/04/2024','17/04/2024','12:58','19:58','12:59','alert_set'),
(6,3,'12/04/2024','12/04/2024','13:19','13:20','13:22','alert_set'),
(7,3,'13/04/2024','13/04/2024','13:19','13:20','13:22','alert_set'),
(8,3,'14/04/2024','14/04/2024','13:19','13:20','13:22','alert_set'),
(9,3,'15/04/2024','15/04/2024','13:19','13:20','13:22','alert_set'),
(10,3,'16/04/2024','16/04/2024','13:19','13:20','13:22','alert_set'),
(11,3,'17/04/2024','17/04/2024','13:19','13:20','13:22','alert_set'),
(12,3,'18/04/2024','18/04/2024','13:19','13:20','13:22','alert_set'),
(13,3,'19/04/2024','19/04/2024','13:19','13:20','13:22','alert_set'),
(14,2,'13/04/2024','13/04/2024','15:08','13:26','18:24','alert_set'),
(15,2,'14/04/2024','14/04/2024','13:25','13:26','18:24','alert_set'),
(16,2,'15/04/2024','15/04/2024','13:25','13:26','18:24','alert_set'),
(17,2,'16/04/2024','16/04/2024','15:06','15:07','18:24','alert_set'),
(18,3,'15/04/2024','15/04/2024','16:45','16:46','16:47','alert_set'),
(19,3,'16/04/2024','16/04/2024','16:45','16:46','16:47','alert_set'),
(20,1,'13/04/2024','13/04/2024','17:02','17:04','17:04','alert_set'),
(21,1,'14/04/2024','14/04/2024','17:02','17:04','17:04','alert_set'),
(22,1,'15/04/2024','15/04/2024','17:02','17:04','17:04','alert_set'),
(23,1,'16/04/2024','16/04/2024','17:02','17:04','17:04','alert_set'),
(24,1,'17/04/2024','17/04/2024','17:02','17:04','17:04','alert_set'),
(25,1,'18/04/2024','18/04/2024','17:02','17:04','17:04','alert_set'),
(26,1,'19/04/2024','19/04/2024','17:02','17:04','17:04','alert_set'),
(27,1,'20/04/2024','20/04/2024','17:02','17:04','17:04','alert_set');

/*Table structure for table `set_reminder` */

DROP TABLE IF EXISTS `set_reminder`;

CREATE TABLE `set_reminder` (
  `alery_id` int(11) NOT NULL AUTO_INCREMENT,
  `medicine_id` int(11) DEFAULT NULL,
  `start_date` varchar(45) DEFAULT NULL,
  `end_date` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`alery_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `set_reminder` */

/*Table structure for table `transportation` */

DROP TABLE IF EXISTS `transportation`;

CREATE TABLE `transportation` (
  `transportation_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `image` varchar(5000) DEFAULT NULL,
  `license_proof` varchar(5000) DEFAULT NULL,
  `type_of_vehicle` varchar(45) DEFAULT NULL,
  `vehicle_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transportation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `transportation` */

insert  into `transportation`(`transportation_id`,`login_id`,`fullname`,`place`,`phone`,`email`,`image`,`license_proof`,`type_of_vehicle`,`vehicle_number`) values 
(1,17,'sona','mca','94795116655','sddfj','static/c137383b-91b3-47c2-a0da-43d909daea4bHome page2.png','static/77e40c6b-bf7c-4185-b89c-eba5da7ef75aScreenshot 2023-06-30 202020.png','sggg','sghgh'),
(4,17,'sonar','mca','08547418286','sddfj@gmail.com','static/c137383b-91b3-47c2-a0da-43d909daea4bHome page2.png','static/77e40c6b-bf7c-4185-b89c-eba5da7ef75aScreenshot 2023-06-30 202020.png','car','kl8524'),
(3,16,'anjali','mca','08547418286','anjalirajeevnair@gmail.com','static/1c6d77ed-c215-49e7-a806-b719d06b9ab3admin home page.png','static/369b4a17-9bdb-4d6a-8f33-d7ea16da172cadmin manage emergency parking.png','car','kl8524'),
(5,19,'carr','cherpu','947951166551','sddfj@gmail.com','static/c137383b-91b3-47c2-a0da-43d909daea4bHome page2.png','static/77e40c6b-bf7c-4185-b89c-eba5da7ef75aScreenshot 2023-06-30 202020.png','car','654949'),
(6,20,'carr','cherpu','947951166551','sddfj@gmail.com','static/c137383b-91b3-47c2-a0da-43d909daea4bHome page2.png','static/77e40c6b-bf7c-4185-b89c-eba5da7ef75aScreenshot 2023-06-30 202020.png','car','654949'),
(7,50,'Minerva Pugh','Sunt amet sit nis','+1 (764) 794-1284','cidutag@mailinator.com','static/c137383b-91b3-47c2-a0da-43d909daea4bHome page2.png','static/77e40c6b-bf7c-4185-b89c-eba5da7ef75aScreenshot 2023-06-30 202020.png','Veniam dolor anim s','471');

/*Table structure for table `transportation_request` */

DROP TABLE IF EXISTS `transportation_request`;

CREATE TABLE `transportation_request` (
  `transportation_request_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `transportation_id` int(11) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transportation_request_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `transportation_request` */

insert  into `transportation_request`(`transportation_request_id`,`user_id`,`transportation_id`,`description`,`date`,`status`) values 
(1,1,1,'jgjg','30/07/2001','ACCEPT'),
(2,3,1,'okk','2024-04-16','ACCEPT');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `caretaker_id` int(11) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`caretaker_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`,`gender`,`age`) values 
(1,7,4,'amal','jj','thrissur','1234','ama@gmail.com','male','12'),
(2,6,67,'New One','Main','Thrissur','123455','amsnms@gmail.com','Male','12'),
(3,7,51,'Nee','ncnfnf','thei','999999999','anutjtkrk@gmail.com','Male','12');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
