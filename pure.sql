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
INSERT INTO `virgil_grant_keys` VALUES (2,'be48aa42-e792-4933-8e47-4e5f27910c09','S��=i���U�o���z���*���Ζ���;羚�$łC�@�Xa��e���`%$-t�4�d�|',4735795942,'�$be48aa42-e792-4933-8e47-4e5f27910c09\Z@S��=i���U�o���z���*���Ζ���;羚�$łC�@�Xa��e���`%$-t�4�d�|\"<�@$z�Mo{Tw��U�^o�ES�ܒ��,q�U�Y/{$0�w�E��N�j��\n�r�b��*7J{<�V@(�ɹ�0慚�\ZS0Q0\r	`�He\0@��K��� ���l�X��	mU� �����Ȏ@ĚUl��c�K;]޺7�I&��1��:ϥ *A�p�1-tcT�l�W�9�!bWk�\\)gr�w�\n�714��}�Y����V\n��p=�@$N�Xi�g��ݒ');
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
INSERT INTO `virgil_keys` VALUES ('be48aa42-e792-4933-8e47-4e5f27910c09','242562b6-98b2-44f8-b7ea-98d22798a94e','�$be48aa42-e792-4933-8e47-4e5f27910c09\Z$242562b6-98b2-44f8-b7ea-98d22798a94e\",0*0+ep!\0A�������Mi�mŕ޼��v#z8ӧ�|*�0�v0�>	*�H��\r��/0�+1��0���\n<�ۧ.�n0+ep��0��\00*0+ep!\0Z���#?�Nh���]a�U�2�,f��?v�c0(��q0\r	`�He\00A0\r	`�He\00<(����%��vi�M};�����\\�B�t��̪P�X\0)��n��֔�2�0Q0	`�He*���R\n�;�O�=�*4�0�f�7���Ƙ8L�̍�o\"�|רuSͫ=�Ue�{�[��[��&+��0���\n���\'��0+ep��0��\00*0+ep!\0�������o���w��\"o詫 ���%���\'0(��q0\r	`�He\00A0\r	`�He\00OJ/x�����RWm��@�e�`�cSᤞ�*��5;��+�\n�u��}�0Q0	`�He*���N0: v�߁����0��p��\'����Nn���q���/?�UPBW�D�����T����o߀�9)0&	*�H��\r0	`�He.���$\n���#��0\r*�H��\r	�0\00�0\00\r	`�He\02�{�	���!��\r�&��bU�Χ���w9-\"!\'�\"�J	���<������5{dZ�x��ݓq��Du�9����y\Z{`Qb1�Ȥ��D5��?(7H�T-ZV���Ց����0��\r�r�����w���\r{h�5:���$x��s��K����h0���U����kU��#�����[2\ZS0Q0\r	`�He\0@f�ffn���ú��3��ɬaމ^���L]|\n���V���f?���jH���]J���� �'),('be48aa42-e792-4933-8e47-4e5f27910c09','a3010954-066b-4b99-b7c6-ff6bb03e517f','�$be48aa42-e792-4933-8e47-4e5f27910c09\Z$a3010954-066b-4b99-b7c6-ff6bb03e517f\",0*0+ep!\0�����Sc\Z�x>�gW��`2�0~�!��1�5�*�0�v0�>	*�H��\r��/0�+1��0���\n���\'��0+ep��0��\00*0+ep!\0�\n/�͹俾B\'/�lSp��ΰ�#x@���J�0(��q0\r	`�He\00A0\r	`�He\00��A�K	�Z׺�=�Jt��Yl��x�O��p��Jn���\0��x!>�C�0Q0	`�He*�Ӿ*h�����\n��0��ʜ���o��h�,;�2�!DN3�z5h����R-������0���\nخTH�dW0+ep��0��\00*0+ep!\0;����ۣI�jv��\'=`���ӷ�˰��0(��q0\r	`�He\00A0\r	`�He\00(y�\'����\Z>�.���aw�T�MIw��ث�p[��;���V�G�p�0Q0	`�He*���@,�u:��v ��0O��[�u��{.�9��?u�` jX�-����$N(#	SZvN�y�iopj�0&	*�H��\r0	`�He.������F\nx�0\r*�H��\r	�0\00�0\00\r	`�He\02�%�	r�%)�%\rs|J�B\"�׉��u�P�����%d���?f��o=�\n�J8�$�։Ƕd�Qh�2�����	5�� �Z<�P0��#O�}j��g`���B?��7�Z�F6\nx9z���a�`��l\0�sE���C������0C���j\Zx��\0�n�J���U�����͖:SZP\ZS0Q0\r	`�He\0@�6��z6j�}�o�-�D���3\rɒR_c�uC��w/��K��\\b2D]�\0��u|\0�7GR��');
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
INSERT INTO `virgil_role_assignments` VALUES ('6fd13117-717c-4bfb-aee9-044a7910005e','66999ad1-2775-41a4-9fbb-96201bc8202b','�$6fd13117-717c-4bfb-aee9-044a7910005e\Z$66999ad1-2775-41a4-9fbb-96201bc8202b\"<�ۧ.�n*�0��0�?	*�H��\r��00�,1��0���\nخTH�dW0+ep��0��\00*0+ep!\03�e\r��g&���I��D�F���%�ˡ7,BR[:0(��q0\r	`�He\00A0\r	`�He\00\rY\Z�K<`6\n8{w?���%��X� ��i����$>�\"����4�]�0Q0	`�He*��f�6W��[\n걕0�c�Z��0�h(k�-��l:�O*��>B{_�\'�o\n�%>m�i�2k���0&	*�H��\r0	`�He.�S�F��\\�!��0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+��ϖ�2�n���������ւ�i%,��-�{��Kcݠ��4vћ��oeoEcd�v,��C6�Q2�����_�P�=���X�D?�z����t�P��ڠb��l�[�5\\K݇�8�QՇ�1ʯĜiW�Gh����0a���?u���y\\g�Ĝ��6c{b �ݵB�D^���C1	U�|M���`}n�_�D�&��٬�>�pb=GV{yZf��d�v��%2�<̨L?��;V/CTmS�(e��AL$��5=���/u��@�(W��񟟐|�\0WF��cI�C\ZS0Q0\r	`�He\0@P6Tn>��3�(9�f��f�8ѹ�}�z��N���Ek��,�.��=Ϛ��1�:��KGG\n�');
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
INSERT INTO `virgil_roles` VALUES ('6fd13117-717c-4bfb-aee9-044a7910005e','V$6fd13117-717c-4bfb-aee9-044a7910005e\Z,0*0+ep!\0\\ˢ�U�A���R�x3�]1k�8��쏗>�<�2�\ZS0Q0\r	`�He\0@MW��0b�ID��Ƕ�N���>-8O��U�SGH���~�k5��p�]��$P�ӑ��]�8�$');
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
INSERT INTO `virgil_users` VALUES ('66999ad1-2775-41a4-9fbb-96201bc8202b',2,'�\r$66999ad1-2775-41a4-9fbb-96201bc8202b\Z M�s���Zyݓ؀�����ѫ��D��J�ǉQ�n\" ��l7#�gA��r7vլ(!��5������0�6�*,0*0+ep!\0ޓr�Dex�Q~��^E����ld��\r1��!���K2`}�(���	lf��*�PHULi�m�nn����f�F_Ig	��k�	��.-�g�w��.���� �16�{L���dA�\0��0��o�;�3�#f�U�:�0��0�?	*�H��\r��00�,1��0���\nvY����<b0+ep��0��\00*0+ep!\0���ц���\"������C�I�w�LS���/��0(��q0\r	`�He\00A0\r	`�He\00h������\0���������¹�R.�C��}h����p�_+:5��_.0Q0	`�He*�r.XE&ċ�~��̇E0LU���� ZpPM)MN����A���@���MU2��޺K`��mx�S5,v��0&	*�H��\r0	`�He.R	t?瞻��y��0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+��D�q&䖭�(�	��^�F�}��G�j,������/����3�n:\r4��/>Ͼ���/[���l�9�1z���,\n��s�ứ�+v�$=[Ҥ�|Wg �qʯ��ͮ��u��y����~�(�pxTV�#�Z�S7��,Y8ؓҁ�֨\0�_�g��y�������$�F��q����F��)J�w@@��S��r�|:��Y���c���IE��O�k@����\0`�]ԝ��H��Pnu��p\\.J}��k�n2O��vb��5��[.��W�a��B�0��0�?	*�H��\r��00�,1��0���\nvY����<b0+ep��0��\00*0+ep!\0L���}���\0H�w<;�hP�9`@��}�ώ$0(��q0\r	`�He\00A0\r	`�He\00���ʚ����*�4k��`�1�E���\n;���a��l�Tw�9$O��0Q0	`�He*lJ2�ɩ�ȫq���0�̥��ş�a�B�r>���$�!�Oi|A�5���i�ɉ<��d����,0&	*�H��\r0	`�He.$�+�hҵ��͡0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+��U�Wc�O�z(T1ŦK�N9��\n�4h�^��ڕ�Om��y�N~r��b�L�����KZl����6��W\Z���w�F�(P�nw�5�g��s�[�B/�	\'�9ˌѓ`�p7���I��l�8i��S+���\\n<��m�$E������>�\'ݭ�M>�#⟚?ts����n�W�hl��Oh0ȃ��r#B��Nt�1ǃb���A���w �	w��a�\\+_/#<��ˣ]�ID�y�\rz�ey\"�a�����^̬%�����жn�Jxm;�Fԉ]W�DH@���!��Rq[�JP�+g?R&���������*F��#5G�}\n�k�a|.�a�핅w��Ʋ��.��ֵ�w��.���)F��d��.�)�� @\ZS0Q0\r	`�He\0@��Vw�b�[�1�$�����\nf�m!A��rR����q4���<?-*�{��7LR���\"Ao�T4?��`\Z�@��Et�a�{Mb�~ݠ��(\'A�H��E���ɻo>P=hD|K���)�ڱu*A�Y{�o��\rD<!��o���l�!�sK��xA|�3H��qd�\'7AA�v��Y;I�#��[���0:A`��F%��R0������T�Q_��!*9(Q9���{��n���M�4*N��DN���\"-.�/�'),('be48aa42-e792-4933-8e47-4e5f27910c09',2,'�\r$be48aa42-e792-4933-8e47-4e5f27910c09\Z ��(\\x��jXG �\n!�L���{�	h䨦��\" ��g~w���m�]�.�-q���N�org�r*,0*0+ep!\0@�drn\nw��C�H.M�]�i�~˨��B�[֗�2`�)\\	���<�]1���r��]a1�R*���2�c�X�o��`��j�\ZO��h�.ɓ Y�1�ب�\r�\rk�O\Z+��\Z�)�y[�}�%�\\	H�i:�0��0�?	*�H��\r��00�,1��0���\nvY����<b0+ep��0��\00*0+ep!\0��h�����h�;���\'�َp�4�\"��0(��q0\r	`�He\00A0\r	`�He\00���\Z,0/دpj���\"\ZvM��n+�B䬷�0�^�L5G�o�*��0Q0	`�He*�7�\0J�����p�@�W0��@��ǘ��75�v��8��6�����5�Oѕ-i����\'�77m0&	*�H��\r0	`�He.�B���-3����0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+���O���`k��8��ڣ�>�hH��!�����C `�\\��/�\'�/���U�<O�����T���(��t��\0�p�*�D���?��d&+\\\0x�$9�p��q|f�p�$tɊ��I餂~\r�>��8H�,(�1$<슝���Jvk������R�l]�$n��N�W\\�\\�:�H��ׁ+@�d\n�[�4j����8.;$v�����^F������>uE�Z�j��Ǭ�wx&�Hl�]���z���)f�냬ES.\\*���<���G!�0���9�et~`\0��6�nV�B�0��0�?	*�H��\r��00�,1��0���\nvY����<b0+ep��0��\00*0+ep!\0����ʰR������K��bz{�(P煈@�޸0(��q0\r	`�He\00A0\r	`�He\00n�瓷l�,�N2�OZzp��Ƿ���-��m�`��=6�y������qˆ0Q0	`�He*���������y��0���ϐS�c.&!\n���դQ����^�w��R�m�-�WK����3O}0&	*�H��\r0	`�He.���u#VO�%��0\r*�H��\r	�0\0\0��0\00\r	`�He\0�0\n+��|\n�p\"��v�5)~;�����\'Y�9!��6�M�@ӑÝ��N��ט[ֻ������v=��ZtȘ/�,�R�68�YB~7�g|r������nf߆w���-�L��:�-x�lDD�2ӹ�s�.�J��1���A��5e�5Έ�U�d��}#����{t�>а��]8L��H\n�8�����ܪ!�ą�DV~��lB����\'��~\'��w�L����(�^���Df��9Q�lcu��\r*�Q� j��/ogkya����_kz�v�ﳊ���1%&��aAJ\0w�=JP��Z���Y}���35����H\Z�-l�w�<т��6�O����-_?X�����.��OLn8�M�Od��	f���%a/\ZS0Q0\r	`�He\0@��+�GPk�>�g)���،��\Z*t���k=����-��?*�(���M%�ٖ��rLoԓ\"A?_�j0����gIC����:�������:�_�?�ۦ�k}i������U��^��bW&���*Ab��$\\Kn�f���;�ԯԌ�;(?���_��ŽTJ��c���ֹ瘱5U��Lm��|��o]0:A���\r���az�_֦�&��\'�N�\Z��r�>�t\\\\b�<��\\�ȋ�2h��p����^���Q�EZ');
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

-- Dump completed on 2020-02-20 10:52:25
