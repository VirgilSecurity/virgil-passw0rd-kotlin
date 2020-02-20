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
  `expiration_date` bigint(20) NOT NULL,
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
INSERT INTO `virgil_grant_keys` VALUES (2,'cd509ac0-9a38-4eba-ab77-abf50023247e','�J-��ZgFZ���4��k�%�[=x2F�id0�_<��%���.$p����-�Uđx����',4735795471,'�$cd509ac0-9a38-4eba-ab77-abf50023247e\Z@�J-��ZgFZ���4��k�%�[=x2F�id0�_<��%���.$p����-�Uđx����\"<q�������r���\'҆��r@o3�ܣ��,\\w����ٵc�L��h���H&��[��(�ƹ�0����\ZS0Q0\r	`�He\0@k}�eF��Ʋw��f�i	��X�Ӿ�R���v��6�!�����T��SP�L��`���<E��N�� *A`���8������H|���M\rdgʝYJ��.�$�q��J�����.���\"� {��$�>�K��$�');
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
INSERT INTO `virgil_keys` VALUES ('cd509ac0-9a38-4eba-ab77-abf50023247e','43069438-826a-4b93-85ac-bab0bf75c368','�$cd509ac0-9a38-4eba-ab77-abf50023247e\Z$43069438-826a-4b93-85ac-bab0bf75c368\",0*0+ep!\0IG�cg�U|y \ZE<�Vn�%3�s��u0+�5�*�0�v0�>	*�H��\r��/0�+1��0���\n�+�È�-0+ep��0��\00*0+ep!\0�V�3����/A��!��CL�+�\0��/�ʔ�\Z50(��q0\r	`�He\00A0\r	`�He\00=1�K`lx��K�3��!:����T�f���n�����U\r���3��0Q0	`�He*q�VZ^��ĺ(�9�0͎��0c�޸*�2&\0|\'K_�Mlj�v���e�t)�>#am�_%һ�/$L0���\n��P�>�0+ep��0��\00*0+ep!\0/�\'�y���%㌹�W5ԃ!ufs���ڹ> )0(��q0\r	`�He\00A0\r	`�He\00�u������i�S���\'���{%Fu\'�wQ}�>4��kY�ߠV�n�O�w7>0Q0	`�He*���%걸8K�D$��70�$�lG~��9mMB���ނ�\0L�\'�N��G�ݝ����\\FPX�G��*r[O�0&	*�H��\r0	`�He.o\"`�~`CT�K��0\r*�H��\r	�0\00�0\00\r	`�He\02����Κ��.[\Z\0\0O�K��1\\Se�:o�-6ܬ �es;�8�f�h��,�r����D-�l=s�ֹ��7�cF!�����\Z���{\\9%)՚Svi����0@��\"�t���.~�\'����~��~ƚ�e5o$�I�����g��/U!{�c����,���~���)��~���љk���ygU\ZS0Q0\r	`�He\0@��t>��@[��k��H�t�%����7�\\<e�S��FR�9*C�\r�t)\"�y���A��s\0|�M\"c��'),('cd509ac0-9a38-4eba-ab77-abf50023247e','df387224-419e-4e64-944d-1485694ecb8c','�$cd509ac0-9a38-4eba-ab77-abf50023247e\Z$df387224-419e-4e64-944d-1485694ecb8c\",0*0+ep!\0�����c�_3?FP�eZ��+cx=�:�A�*�0�v0�>	*�H��\r��/0�+1��0���\n��P�>�0+ep��0��\00*0+ep!\0�5\\��Hn��}\Z��Z4uUs#��Lz��b��5s0(��q0\r	`�He\00A0\r	`�He\00���~P��1pS���Cҩ�&�.X��UkeJۭt*��,�\Z��>�R0Q0	`�He*�L��=��H�0����0lӌ��[��#�x���d�YM����*����]��Qg+���h��\Z6{F�0���\n^Y6ͻU~0+ep��0��\00*0+ep!\0:�B�gπ57�EO/V��=~�GL��BX(y�0(��q0\r	`�He\00A0\r	`�He\00�W!B�]�m$��J��N��Ȱ{cE�ԮC:��P\\�w�|�HRe�70Q0	`�He*�\"�@�ه�	.��ȾF�0߾�:�y�&W�H�Q2���t��9���	�2���z|S����Ӽ��0&	*�H��\r0	`�He.�3����!i�ۡ0\r*�H��\r	�0\00�0\00\r	`�He\02� ���n8��먽��_��Vw�o�aw�jAL�>��	/�[7~Y�bY����\0i�`s�Y�Ao�)�oU��T��tS�w0����n�H�\"������9]��gZ��Og��Τ���̉�\n��a�\']���!E�j-;C���g�S}�梍�\n.��V4�uj��+~�Zh\ZS0Q0\r	`�He\0@;�Qjf^���٭s7�\n�&���Iqm��$F8^}i��O�{L���-��������(҈�T');
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
INSERT INTO `virgil_role_assignments` VALUES ('6ff0177a-f155-4f65-a120-02a550a56fed','5b61ad08-ee4f-4644-b43e-fba28c75b4eb','�$6ff0177a-f155-4f65-a120-02a550a56fed\Z$5b61ad08-ee4f-4644-b43e-fba28c75b4eb\"^Y6ͻU~*�0��0�?	*�H��\r��00�,1��0���\n�+�È�-0+ep��0��\00*0+ep!\0脄��8]jr���О�����<R���g�[���0(��q0\r	`�He\00A0\r	`�He\008�,�/����~Wi���-�rwg�f��kfyE�%�\Z�=���:�\n���0Q0	`�He*gI��똻y!q�f\ng0G��<�I-�	&�A9\"��5DG�I��b9�)��6�O!�r��Z�\0�\\0&	*�H��\r0	`�He.��Ґ���*|�0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+��\n�:����899�D�%�;�T^����o�������hT{��Q\"��/oԻ��^��.�\"�s�^�˙B&.���]6k���JL$��?Ɍ��3��LfVj(�츺ҧ{��@v��Ǹ#��8w�sF�P�É[gS-���zg>;�(H�{L#���w�\' ��HP�#����w׿^�yX���{�w�|\\��IPGyk��CE�:lR\Z��zlDCע��W���>��S1Ow�S�0�:\\�����T�H��p��I��ը����$��9,ީIȧ��]����\ZS0Q0\r	`�He\0@�~�|旁�Uɘ}ku�hb��5���#��X�M���FF	�4b�o8�ep��)��!{W�`@vf�z');
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
INSERT INTO `virgil_roles` VALUES ('6ff0177a-f155-4f65-a120-02a550a56fed','V$6ff0177a-f155-4f65-a120-02a550a56fed\Z,0*0+ep!\0�. 식�Z�k�&�h�w��Hm	�����4�C\ZS0Q0\r	`�He\0@m9:�ȑ��8�L׫\0c%�S?x���!�[!�I}�����n�e�y�n�t�N�./�=p�=S');
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
INSERT INTO `virgil_users` VALUES ('5b61ad08-ee4f-4644-b43e-fba28c75b4eb',2,'�\r$5b61ad08-ee4f-4644-b43e-fba28c75b4eb\Z TV{n��	�@�e�ʏ]4z��K��\0�Nt�\" TM7�[E�\\��)i[,]���w��Թ�r\0�h��*,0*0+ep!\0����^�)���4�ִ�Lk\Z�H��>1���B�2`8M��\Z3�\'��\'.�G%pу��yt׎\'�0��ꆶC׸�\0ّ�b�xrv�y<D�9`���ޖn�(�Y�G^�s�Np����$�(e�b�V��	�-��:�0��0�?	*�H��\r��00�,1��0���\nE����QL\00+ep��0��\00*0+ep!\0I)�>Y`������B���<:�~8�S~-G��|0(��q0\r	`�He\00A0\r	`�He\00�yczZ.�?��K~�$$����0t��|�>u�pך���+�{5H,�;���0Q0	`�He*N9l(F�a\0A�L���0�)��0�ȉ�����$_5=I��v1�81��A8�(���H����o0&	*�H��\r0	`�He.@�\r���/8�0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+�����XC�K�������ؑ�#��:6�O���M���d�]�z%rl�S�y?!��v3�2��\r���b��/S�/aB���$�_��x�K��x�:���jw�ѭ�Z�����.�x�B�b�I��Gl	�C^�fN#����h���߻*T�9�sT!�Yb��-�\\��\rۥ\" �$�#��4y�?�(ЊG\\,$A�\n ���!G�Xz*�IFf�.���W3w��gEP�`�\\��p��:VnBw��{�r������W��Zp��o,M������9�Z��#� �}mj��B�0��0�?	*�H��\r��00�,1��0���\nE����QL\00+ep��0��\00*0+ep!\0�\\�^��+t(^�k����ɇ<6��\r�b�S��0(��q0\r	`�He\00A0\r	`�He\00���0j���=R�\"Ph�\n����\r�S�H�@u�k���ۣ+�x��9&c0Q0	`�He*W�$��<�ϴZ?N��0��`:��?P~{��Ƅ��+m���Id���ʽnRÔ��$u��I��0&	*�H��\r0	`�He.xW\\�81��	;��0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+����AI��$	��6���-RM%(I���3�F�ټ�˜��jC��;^v�\n6b	�d3:��=2wXNM����ЎU-���6O��6��|�V�X�})��N�佺���k	b<>X����Ϳ\'u�_{h�C��⍃G�YDO�h8����n�ZQ}.x�����T蚭��#C:�N�������/�τ���\0�!�#Z��NW�{��uq�aq�_��륟�u�]�Oau4F�%no�K�\r���q�l���G�a G3޺0�:�C�P��r�6�E�|�~JP���Fy`8��:X7\"���<��0�R��6)�&_��\'o���x�Ĩ���wo\0��\Z+�b����=���JnV��Q\ZS0Q0\r	`�He\0@�*����~��X�h4��>��.�6�	�~o�\"�?m\0�*VDT����J*�8>�X��K���ͫy�\"A�K����<��B�Z�p�_M�<)z�<�����荳vo7~v��w���m�v�q�Qφ^b�u*A�����|G1�M�@t{`�]��q��V�R��}sJ�XJ$�j�uC��j���E}���=�*\rr�0:A��ʿ���\ZP�����x�m!�s�o�?�x~4G�v-o�\n%�S��ő!��,�>�!K,�v�'),('cd509ac0-9a38-4eba-ab77-abf50023247e',2,'�\r$cd509ac0-9a38-4eba-ab77-abf50023247e\Z \Z��\Z��g}5�$�}Dx��2�R�Auo)�Z4(J\" c%�{A��zlҘl^���cX\\\'�o�n�*,0*0+ep!\0�Pׯ��KptSX-R=�9\r�{y��ʤ��7�2`;�o\"�~A;Ք��d�>&u�]���EG�@HTo�PÁZV��h]{���K��#i���1�y\"�*�7b^O?!�.�.��[����kԺ�!��]���\\�p:�0��0�?	*�H��\r��00�,1��0���\nE����QL\00+ep��0��\00*0+ep!\0{cP\n�����/�C6���J	2��N�%X��0(��q0\r	`�He\00A0\r	`�He\00�{�$hI�Y�1�x\"ӿb}�R<6�q>ߢ�u�y(jl$\r[��Ӥ��j�0Q0	`�He*B����)��H|�V_0��ԴA�׀f$�F���i: L�\r�S����ә�>��p��������0&	*�H��\r0	`�He.�����A��)}��0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+����1��Y���%Ӟ��K���7},�-F?�;��}֐�Գ���=�aT�`\'���i=c��GV��k_�bg��+#�%S���p����8�+!����&3D��@�V����Zu\rH�A_/ó9��!&�z�t�{@��+>�������;;:���4����P&Cs�A��Ff���]y��� r��f��s���\r*���di�[aݱ��0Q7G`�{WZ�C�I�W�y�Z{.X�������R��P9���$���޼p�sl�K��o�����?�l���ܖ�)dZ��&B�0��0�?	*�H��\r��00�,1��0���\nE����QL\00+ep��0��\00*0+ep!\0ε��+\'���ۦ�`;�dTtç|̣�C��0(��q0\r	`�He\00A0\r	`�He\00X�I���p���b�|g��]��k�*D�襋�[�C���lPu<���\0�0Q0	`�He*`à��D ��j#�0HFW�����2��#)\0d�5�:TT�]Ǳ�������N�D�+�QA��0&	*�H��\r0	`�He.�\\r�5�G�y�u��0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+�����$m]��$п�&1�>{]��,3�G�L|�AdKPQ�(��\'3@�+��F\rn�R�7����(�nd=�w=�����p�,�/�F�̾1����r��6�M����[Ѧ���A�F�,y���=Ҵ�]Lk?UC�	�Ɋֈx���3!6Ā|$)���5�\"�|���gh�����?oW��3�h{R��R��w	X����vYӭ���Xٯ3�k����/o2̣csA�^�2���w��0A`�u�̥MZ	�Yg�`�J�x���i��|Z:6�,Ԏ���\r>�JPd4���O�q��iy$iQ�ӈ�q��j��I\'J���5j������P���:��h=�n��x��m�ڔ�a�̒z��\ZS0Q0\r	`�He\0@����@���I��������<|�ӱ2Ed/n�C������*Ň��]�py�J2#�bwף��@��\"A�a9!/��O@ʬ� ����axL;��V��*��$��1P�et�; ���NV������\\�)ݧJ*A��nt0=�/��mS����I�.F v;a������:�m��\0�3��|u�L`R�RA)���\\T0:A�5&�e��HV�5-o��\'��z���$A�m\Z�	#�և�Y�@�t)�J�H)Ǜ��r�$Cq@');
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

-- Dump completed on 2020-02-20 10:45:34
