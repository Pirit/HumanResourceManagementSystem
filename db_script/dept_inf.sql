CREATE TABLE dept_inf (
	ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    REMARK VARCHAR(300) DEFAULT NULL,
    PRIMARY KEY (ID)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
INSERT INTO dept_inf(ID,NAME,REMARK) VALUES
	(1,'技术部','技术部'),
	(2,'运营部','运营部'),
	(3,'财务部','财务部'),
	(5,'总公办','总公办'),
	(6,'市场部','市场部'),
	(7,'教学部','教学部');