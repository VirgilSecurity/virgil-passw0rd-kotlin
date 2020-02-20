CREATE DATABASE puretest;
USE puretest;

-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: puretest
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB-1:10.4.11+maria~bionic

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `virgil_grant_keys`
--

DROP TABLE IF EXISTS `virgil_grant_keys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virgil_grant_keys` (
  `record_version` int(11) NOT NULL,
  `user_id` char(36) NOT NULL,
  `key_id` binary(64) NOT NULL,
  `expiration_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `protobuf` varbinary(512) NOT NULL,
  PRIMARY KEY (`user_id`,`key_id`),
  KEY `record_version_index` (`record_version`),
  KEY `expiration_date_index` (`expiration_date`),
  CONSTRAINT `virgil_grant_keys_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `virgil_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virgil_grant_keys`
--

LOCK TABLES `virgil_grant_keys` WRITE;
/*!40000 ALTER TABLE `virgil_grant_keys` DISABLE KEYS */;
INSERT INTO `virgil_grant_keys` VALUES (2,'845e3ad4-09c9-41e0-aad4-61161b5e2a65','g�\Z�e�3;\r�r�g�$I���x[W3yW�295˸Ӛ�S���5x�=��O\rq�0X{$�f','2020-02-17 16:40:39','�$845e3ad4-09c9-41e0-aad4-61161b5e2a65\Z@g�\Z�e�3;\r�r�g�$I���x[W3yW�295˸Ӛ�S���5x�=��O\rq�0X{$�f\"<߳��E�O���c�_��\\�\"�3��C���2��f=�X�N��C�,m�sҁ�_���	�(���0����\ZS0Q0\r	`�He\0@���#�b�Lbt\"��\\Z��	�hS5x�w�����������n��LϦ��\'�:��\\� *A$��˒��-�\'��\'��w\"�Q����܀�EH�s�Y?#A�*mX=P;je~�ӆ�x���!���b��');
/*!40000 ALTER TABLE `virgil_grant_keys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virgil_keys`
--

DROP TABLE IF EXISTS `virgil_keys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virgil_keys` (
  `user_id` char(36) NOT NULL,
  `data_id` varchar(128) NOT NULL,
  `protobuf` varbinary(32768) NOT NULL,
  PRIMARY KEY (`user_id`,`data_id`),
  CONSTRAINT `virgil_keys_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `virgil_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virgil_keys`
--

LOCK TABLES `virgil_keys` WRITE;
/*!40000 ALTER TABLE `virgil_keys` DISABLE KEYS */;
/*!40000 ALTER TABLE `virgil_keys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virgil_role_assignments`
--

DROP TABLE IF EXISTS `virgil_role_assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virgil_role_assignments` (
  `role_name` varchar(64) NOT NULL,
  `user_id` char(36) NOT NULL,
  `protobuf` varbinary(1024) NOT NULL,
  PRIMARY KEY (`role_name`,`user_id`),
  KEY `user_id_index` (`user_id`),
  CONSTRAINT `virgil_role_assignments_ibfk_1` FOREIGN KEY (`role_name`) REFERENCES `virgil_roles` (`role_name`) ON DELETE CASCADE,
  CONSTRAINT `virgil_role_assignments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `virgil_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virgil_role_assignments`
--

LOCK TABLES `virgil_role_assignments` WRITE;
/*!40000 ALTER TABLE `virgil_role_assignments` DISABLE KEYS */;
INSERT INTO `virgil_role_assignments` VALUES ('0955dde9-6c18-47c4-b37c-21f2a98a14d7','b8f477a8-634e-4da4-bb52-b7b28acf0274','�$0955dde9-6c18-47c4-b37c-21f2a98a14d7\Z$b8f477a8-634e-4da4-bb52-b7b28acf0274\"�=��I�*�0��0�?	*�H��\r��00�,1��0���\n��Ƥ&�0+ep��0��\00*0+ep!\0_;¯�߀IX�h�\r��G��tT��f~����M�\00(��q0\r	`�He\00A0\r	`�He\00D7�\n�Ly�XFI������E�l�dM��\0/�|��:�?����38Ge�0Q0	`�He*Z���xd^�gUM��0a���Sh��N�C\n\0p���L�0�!�U3��*I�f�y�	��Z1w�S:0&	*�H��\r0	`�He.eh����〸��A�0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+��V�M����A���l���y�~��X�Tw���D��=��+�֗AH�����ǝ! 	��&�y}�/��J��Z��I�\rQ-����`_�ǂ�f���,��O��Q�!�\Z��z�i�����/~�hx�8$�E��u�ip����S8��y̯̐��	��m˰]������h�j�!6�@�a��qW�D��L��5��r\\����i}�e����@O�%yܭ0����p�C����fK~���Q��1,�����s�`le�9�����^}���1��L��B����n���^St=�D\ZS0Q0\r	`�He\0@\'�w�Sܛ(��]I��j~T�cޱ\'>RO �u�b��i}�FZ�,2\'�ĕ�T\"�q�^Md��G');
/*!40000 ALTER TABLE `virgil_role_assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virgil_roles`
--

DROP TABLE IF EXISTS `virgil_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virgil_roles` (
  `role_name` varchar(64) NOT NULL,
  `protobuf` varbinary(256) NOT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virgil_roles`
--

LOCK TABLES `virgil_roles` WRITE;
/*!40000 ALTER TABLE `virgil_roles` DISABLE KEYS */;
INSERT INTO `virgil_roles` VALUES ('0955dde9-6c18-47c4-b37c-21f2a98a14d7','V$0955dde9-6c18-47c4-b37c-21f2a98a14d7\Z,0*0+ep!\0P�\r�q�J�n�I&���JD��1u>�`��ǃm\ZS0Q0\r	`�He\0@ʎ��������L�JV%H@=\rA���Z�t�(����4��o�1g�r4��Y���Q���');
/*!40000 ALTER TABLE `virgil_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virgil_users`
--

DROP TABLE IF EXISTS `virgil_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virgil_users` (
  `user_id` char(36) NOT NULL,
  `record_version` int(11) NOT NULL,
  `protobuf` varbinary(2048) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_record_version_index` (`user_id`,`record_version`),
  KEY `record_version_index` (`record_version`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virgil_users`
--

LOCK TABLES `virgil_users` WRITE;
/*!40000 ALTER TABLE `virgil_users` DISABLE KEYS */;
INSERT INTO `virgil_users` VALUES ('845e3ad4-09c9-41e0-aad4-61161b5e2a65',2,'�\r$845e3ad4-09c9-41e0-aad4-61161b5e2a65\Z l\\��3P��\r�	��}Mp���/����Qۄ5�\" ��_� �T�bЄ;^��l�O7�HF�6�m~*,0*0+ep!\0BW�Y��Ӳ2�NW�u�5�+A��;%�n2`9&5��<���)W����@���c�r����3Zeq���#I.��=P��a�l���08�l��S*���؈^	��_uUv�O���m��\nD!K�ZT�E�:�0��0�?	*�H��\r��00�,1��0���\n��VM�Y�0+ep��0��\00*0+ep!\0��Y��o��ܹ.�����e:��PH��A�5�0(��q0\r	`�He\00A0\r	`�He\000A�v\0a�zP�2vH��Įi��)�)_��u~��|\')���a3��n��0Q0	`�He*����M�^)�b*>&7�0JAL{$50`ۋ��ܪ����v=L�=��]�֝c��7��\0�x\\\Z�\nv$0&	*�H��\r0	`�He.C��t��:�I�0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+���\n�,�H9[PYBN�\Zrz�Uq3e�԰�&�7��*�W��#|9*�`-����\\�v�Ǘё���x��|��-N�qQ|�T��6�3\\�|�5�\"љ��wQN�A�7\0}_�g�jI��z���zH%~�i���P���(�͎$��ޜ�\Z���	\"�0O�L7�q4ו+j���z+y6�xݝ�ω�}��P�*�(�8�c#�b�Yڐcoͮ�y��[B�A?1�1bag�yEZF:O $pN�S�H�F��W���;g�/G���\Z�b��n�S��B�0��0�?	*�H��\r��00�,1��0���\n��VM�Y�0+ep��0��\00*0+ep!\0\"�grH�|g���k1i�x��Mg �!+΢�Q[0(��q0\r	`�He\00A0\r	`�He\00ح���a@�`�!�8�73�2��^�p��Pi�mx�� qZ�lkV�m\Z�B^0Q0	`�He*X��+��\"tۅ��x�0�E��g2��;MPcK{3�Z��Iʐ\Z7��2SMU�����~VL300&	*�H��\r0	`�He.�m���;׹PΦ�0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+���{�m?�Rg\Z0�h�%\rL[�Kpv&�9!SmvS���fq���!���Z��+(,�Qi�[\0�Z�.����AΝ��Q����\Z9�d�P�pۇ1�\ZDΟa�и����]	��h���[�s4�Y��K:؟����ev�}6�i8�8qP0��G���R$b����^�������`!�:��!�V�L-��%\0;[+�u�pl*�_��`JS=�cTT����Q2^�S7�nĝ�Q�d$`6��i�Ώ0G�ښx]�@�xp�7�fe����]tn�F�E��/JP��� =��ۨ����\n��E��\0�b�T�g�:Σ��$V%�zQM�\0v؛�ߎ⻏d�A�?Nv�_�*~\'�k����v@�\ZS0Q0\r	`�He\0@����S���]���N���-ۧ���u��T��5�>�qo�:�X�?T��cB��e�C���\"APT{{7�DWc����l��w���L�!Ӂ��ņw�=ug��?�r�$��AWrE�*�蓖O�(*A`M]�U���\"25P�� �8�!@l9OD�p:�1���r|jn�_?��O�v�d64��/�V�W0:A�8��zJz+DY)����9>Pi��#�N���]�Iɬ�#�ka>)bDEm�I\n�;!��φB'),('b8f477a8-634e-4da4-bb52-b7b28acf0274',2,'�\r$b8f477a8-634e-4da4-bb52-b7b28acf0274\Z �����{%�\rτ�����E����K_�(ﭞ�Uo]\" �a�����nW���|X�⛄�i<���}d$Τ�*,0*0+ep!\0���Z�m\0��=���G9��G�*����>{8���2`�t|񹵫\"ix@p�F���#&f�+y ;Ƀ8�����ҫv#� 2\\��\"���#�kG��L\\�o��J�m� �y��h,C!挏�zN��\"ch�6�:�0��0�?	*�H��\r��00�,1��0���\n��VM�Y�0+ep��0��\00*0+ep!\0&��i\n�y���Ֆ!L���=)o\"<-<2�M��0(��q0\r	`�He\00A0\r	`�He\00�d|ڶ?�*&H$�?D��O=����Zgn�(X�!n�t���_\\����D��0Q0	`�He*��H���Vl�S^���0ԶnZ��\\P��*��P�O:��\"���@5-\\����r\r���@ΜJ` �#�0&	*�H��\r0	`�He.e�]�z�1����$�0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+��-(�Y�zo�]+�Q�Lc�G<�l�R;\nI��E�̈́�@��RN&Q6�ى�\"�4��,�~\n�\'�	��Nb�\'���щ��(�~�M�>q���iT�H^���=�z��0+�t�m�K�K��8�+\'S�Jv��xQ�n죙y���/r��P����\"A��V+��ޠ�4�e��涐n�*`o�M~���4���P_x�xQ�ͥ��U�m���k�\".4�h�Z���mQ�i��0�@;���\r�L���H�!w3�I�S?�k_�(�׷u�&q�����B�0��0�?	*�H��\r��00�,1��0���\n��VM�Y�0+ep��0��\00*0+ep!\0ސ�?� �|-���?��cS\r�[V�6/x\0��\0R0(��q0\r	`�He\00A0\r	`�He\00�z�S�tB2�f33�n�X al�_�A���Ф%�s��r��lP��n0Q0	`�He*�6vL����6��Z��0���(��缁��Q��h�R�$Ɔ;l\\Z̭%��i����\Z�F\\�0&	*�H��\r0	`�He.�E���V\nO��0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+�����r���su)4�.j1y��W�,�\n��-� �E�x���d�\'��D񵯠��oJ����X|߶��F�`�ւ�	E�҈H�\";)]���\0��ǘc�=e<�|L���=t�=�t:��T#.�����aA_�;Q�����B\"\"�o�CZ9N��\\X���@\\y�T���gC��E� ��Í����%�V&F��Y��^Eȵ��7��U�5?�N�bo���|��f�aF���5H(���L4��~��?)����)\n���><�����t�.�*Dk�@�B�5a���JP�^T�ƺ��$.�*̾�+��+���S��#_�űI�/	|a׾O~$��b�(�(�Sؿ��Rix}9hs���J�\ZS0Q0\r	`�He\0@d}�����/E9z�}>���4i\r����.�a�J�V��HZ�Iu�H7m�@�S��9�-g�k2�\"A}�8K&*�Ҩx����#�i{�ٸ�����܆K�֎޻W���|����y�f�h�]�od	�4U*W*A�kB0���J�έ���*t]�Y���Rz���5kq*ZS����3��3M�%�)��cRl�S#���0:A~�<;\ni�:)�o���5\"��v29�[���&�\r���t�\'*�����\"�1�a�{���e��/');
/*!40000 ALTER TABLE `virgil_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-17 15:43:32
