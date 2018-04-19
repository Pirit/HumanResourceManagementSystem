CREATE TABLE job_inf(
	ID INT(11) NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    REMARK VARCHAR(300) DEFAULT NULL,
    PRIMARY KEY (ID)
)ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

INSERT INTO job_inf(ID,NAME,REMARK) VALUES
	(1,'职员','职员'),
	(2,'Java 开发工程师','Java 开发工程师'),
	(3,'Java 中级开发工程师','Java 中级开发工程师'),
	(4,'Java 高级开发工程师','Java 高级开发工程师'),
	(5,'系统管理员','系统管理员'),
	(6,'架构师','架构师'),
	(7,'主管','主管'),
	(8,'经理','经理'),
	(9,'总经理','总经理');