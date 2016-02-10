/*ɾ���̳����ݿ�,�������*/
drop database if exists shop;
/*�������ݿ�,�����ñ���*/
create database shop default character set utf8;

use shop;
/*ɾ������Ա��*/
drop table if exists account;
/*ɾ����Ʒ����*/
drop table if exists category;
/*ɾ����Ʒ��Ϣ��*/
drop table if exists product;

drop table if exists forder;

drop table if exists sorder;

drop table if exists status;

drop table if exists user;

/*============================*/
/* Table: ����Ա��ṹ 		  */
/*============================*/
create table account
(
   /* ����Ա���,�Զ����� */
   id                  int not null auto_increment,
   /* ����Ա��¼��  */
   login               varchar(20),
   /* ����Ա����  */
   name                varchar(20),
   /* ����Ա���� */
   pass                varchar(20),
   /* �Ƿ�Ϊ��������Ա  */
   system               boolean default false,
   /* ���ñ��Ϊ���� */
   primary key (id)
);

/*=============================*/
/* Table: ��Ʒ����ṹ 		   */
/*=============================*/
create table category
(
   /* �����,�Զ����� */
   id                  int not null auto_increment,
   /* ������� */
   type                varchar(20),
   /* ����Ƿ�Ϊ�ȵ����,�ȵ������п�����ʾ����ҳ */
   hot                 bool default false,
   /* ���,���������λ����Ա���� */
   aid                  int,
   /* ���������Ϊ���� */
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment,
   login                varchar(20),
   name                 varchar(20),
   pass                 varchar(20),
   sex                  varchar(1),
   phone                varchar(20),
   email                varchar(20),
   primary key (id)
);

/*=============================*/
/* Table: ��Ʒ��ṹ	 		   */
/*=============================*/
create table product
(
   /* ��Ʒ���,�Զ����� */
   id                  int not null auto_increment,
   /* ��Ʒ���� */
   name                varchar(20),
   /* ��Ʒ�۸� */
   price               decimal(8,2),
   /* ��ƷͼƬ */
   pic                 varchar(200),
   /* ��Ʒ�򵥽��� */
   remark              longtext,
   /* ��Ʒ��ϸ���� */
   xremark             longtext,
   /* ��Ʒ�������� */
   date                timestamp default CURRENT_TIMESTAMP,
   /* �Ƿ�Ϊ�Ƽ���Ʒ,�Ƽ���Ʒ���п�����ʾ���̳���ҳ */
   commend             bool,
   /* �Ƿ�Ϊ��Ч��Ʒ,��Ч��Ʒ���п�����ʾ���̳���ҳ */
   open                bool,
   /* ��Ʒ���ڵ������*/
   cid                  int,
   /* ������Ʒ���Ϊ���� */
   primary key (id)
);

/*==============================================================*/
/* Table: ���ﳵ(������)                                            */
/*==============================================================*/
create table forder
(
   id                   int not null auto_increment,
   name                 varchar(20),
   phone                varchar(20),
   remark               varchar(20),
   date                 timestamp default CURRENT_TIMESTAMP,
   total                double(8,2),
   post                 varchar(20),
   address              varchar(20),
   uid                  int,
   sid                  int,
   primary key (id)
);

/* �޸��Զ������ĳ�ʼֵ */
ALTER TABLE forder AUTO_INCREMENT = 123320325;

/*==============================================================*/
/* Table: ������(������)                                               */
/*==============================================================*/
create table sorder
(
   id                   int not null auto_increment,
   name                 varchar(20),
   price                double(10,2),
   number               int,
   fid                  int,
   pid                  int,
   primary key (id)
);

/*==============================================================*/
/* Table: ����״̬��                                                                               						*/
/*==============================================================*/
create table status
(
   id                   int not null auto_increment,
   status               varchar(20),
   primary key (id)
);


/*�����������*/
INSERT INTO account(login,name,pass,system) values ('admin','Сǿ','admin',true);
INSERT INTO account(login,name,pass,system) values ('user','����','user',false);

INSERT INTO category (type,hot,aid) VALUES ('��ʿ����',true,1);
INSERT INTO category (type,hot,aid) VALUES ('Ůʿ����',true,1);
INSERT INTO category (type,hot,aid) VALUES ('��ͯ����',true,2);
INSERT INTO category (type,hot,aid) VALUES ('��������',false,2);


/* ��Ʒ�������� */
INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('ʥ������',0.01,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,1);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('��������',0.01,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,1);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('��������',0.01,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,1);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('����������',0.01,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,1);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('����Ůװ',199.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,2);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('ѩ��ѥ',299.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,2);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('ŷ��Ůװ',0.01,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,2);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('Ů������',4999.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,2);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('���ܵ�����',3998.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,3);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('��ʿ������',299.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,3);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('����Ӳ��',599.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,3);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('��ˮţ����',399.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,3);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('������',150.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,4);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('�ϰ���',199.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,4);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('�յ�',3599.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,4);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('����',0.01,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,4);

/* �û��������� */
INSERT INTO user (login,name,pass,sex,phone,email) VALUES ('user','С��','user','��','18027364651','soft03_test@sina.com');

INSERT INTO user (login,name,pass,sex,phone,email)
VALUES ('user2','С��','user2','Ů','18027364651','soft03_test@sina.com');

/*����״̬��������*/
INSERT INTO status (status) VALUES ('δ֧��');
INSERT INTO status (status) VALUES ('��֧��');
INSERT INTO status (status) VALUES ('������');
INSERT INTO status (status) VALUES ('�������');

/*���빺�ﳵ��������*/
INSERT INTO forder (name,phone,remark,date,total,address,post,uid,sid) VALUES ('bb','123','��ע',default,200.3,'���������','1000',1,1);

/*���빺�ﳵ���������*/
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('�յ�',200,1,15,201407013);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('����',0.3,1,16,201407013);

/* �����������  */
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('�յ�',200,3,15,201407014);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('����',0.3,4,16,201407014);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('ѩ��ѥ',0.1,1,6,201407014);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('ŷ��Ůװ',0.1,2,7,201407014);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('Ůʿ����',0.1,7,8,201407014);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('�ϰ���',100,10,14,201407014);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('����Ӳ��',12,6,11,201407014);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('ʥ������',0.01,12,1,201407014);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('��������',0.05,10,2,201407014);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('ɼɼ����',0.01,11,3,201407014);

SELECT * FROM account;
SELECT * FROM category;
SELECT * FROM product;
SELECT * FROM user;
SELECT * FROM status;
SELECT * FROM forder;
SELECT * FROM sorder;


drop table if exists privilege_role;
drop table if exists privilege;
drop table if exists role;

/*==============================================================*/
/* Table: privilege                                             */
/*==============================================================*/
create table privilege
(
   id                   int not null auto_increment,
   name                 varchar(50),
   pid                  int,
   primary key (id)
);

create table url(
   id   int not null auto_increment,
   url  varchar(200),
   pid  int,
   primary key (id)
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   int not null auto_increment,
   name                 varchar(20),
   detail               varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: privilege_role                                        */
/*==============================================================*/
create table privilege_role
(
   pid                  int not null,
   rid                  int not null,
   primary key (pid, rid)
);

create table account_role(
	aid int,
	rid int,
	primary key(aid,rid)
);

/*----�����ɫ��Ϣ-----*/
INSERT INTO role (name,detail) VALUES ('����Ա','�������й���');
INSERT INTO role (name,detail) VALUES ('�ͷ�','���������Ʒ�Ĳ�ѯ����ӹ���');

/*----����Ȩ����Ϣ-----*/
INSERT INTO privilege (name,pid) VALUES ('��Ʒ����',null);
INSERT INTO privilege (name,pid) VALUES ('������',null);
INSERT INTO privilege (name,pid) VALUES ('�˻�����',null);
INSERT INTO privilege (name,pid) VALUES ('��ɫ����',null);
INSERT INTO privilege (name,pid) VALUES ('������Դ',null);

/*----����Ȩ����Ϣ-----*/
INSERT INTO privilege (name,pid) VALUES ('��ѯ��Ʒ',1);
INSERT INTO privilege (name,pid) VALUES ('�����Ʒ',1);

INSERT INTO privilege (name,pid) VALUES ('��ѯ���',2);
INSERT INTO privilege (name,pid) VALUES ('������',2);
INSERT INTO privilege (name,pid) VALUES ('�������',2);
INSERT INTO privilege (name,pid) VALUES ('ɾ�����',2);

INSERT INTO privilege (name,pid) VALUES ('��ѯ�˻�',3);
INSERT INTO privilege (name,pid) VALUES ('����˻�',3);
INSERT INTO privilege (name,pid) VALUES ('�����˻�',3);
INSERT INTO privilege (name,pid) VALUES ('�����ɫ',3);
INSERT INTO privilege (name,pid) VALUES ('ɾ���˻�',3);

INSERT INTO privilege (name,pid) VALUES ('��ѯ��ɫ',4);
INSERT INTO privilege (name,pid) VALUES ('��ӽ�ɫ',4);
INSERT INTO privilege (name,pid) VALUES ('���½�ɫ',4);
INSERT INTO privilege (name,pid) VALUES ('ɾ����ɫ',4);

INSERT INTO privilege (name,pid) VALUES ('��̨��ҳ',5);

/*-----������Դ��url��Ϣ-----*/
INSERT INTO url (url,pid) VALUES ('/send_product_query.action',6);
INSERT INTO url (url,pid) VALUES ('/product_queryJoinCategory.action',6);
INSERT INTO url (url,pid) VALUES ('/send_product_save.action',7);
INSERT INTO url (url,pid) VALUES ('/product_save.action',7);

INSERT INTO url (url,pid) VALUES ('/send_category_query.action',8);
INSERT INTO url (url,pid) VALUES ('/category_queryJoinAccount.action',8);
INSERT INTO url (url,pid) VALUES ('/send_category_save.action',9);
INSERT INTO url (url,pid) VALUES ('/category_save.action',9);
INSERT INTO url (url,pid) VALUES ('/send_category_update.action',10);
INSERT INTO url (url,pid) VALUES ('/category_update.action',10);
INSERT INTO url (url,pid) VALUES ('/category_deleteByIds.action',11);


INSERT INTO url (url,pid) VALUES ('/send_account_query.action',12);
INSERT INTO url (url,pid) VALUES ('/account_queryForPage.action',12);
INSERT INTO url (url,pid) VALUES ('/send_account_save.action',13);
INSERT INTO url (url,pid) VALUES ('/account_save.action',13);
INSERT INTO url (url,pid) VALUES ('/send_account_update.action',14);
INSERT INTO url (url,pid) VALUES ('/account_updateHql.action',14);
INSERT INTO url (url,pid) VALUES ('/account_getAccount.action',15);
INSERT INTO url (url,pid) VALUES ('/account_grantRole.action',15);
INSERT INTO url (url,pid) VALUES ('/account_deleteByIds.action',16);

INSERT INTO url (url,pid) VALUES ('/send_role_query.action',17);
INSERT INTO url (url,pid) VALUES ('/role_query.action',17);
INSERT INTO url (url,pid) VALUES ('/send_role_save.action',18);
INSERT INTO url (url,pid) VALUES ('/role_save.action',18);
INSERT INTO url (url,pid) VALUES ('/send_role_update.action',19);
INSERT INTO url (url,pid) VALUES ('/role_update.action',19);
INSERT INTO url (url,pid) VALUES ('/role_deleteByIds.action',20);

INSERT INTO url (url,pid) VALUES ('/send_public_index.action',21);

INSERT INTO account_role (aid,rid) values (1,1);

/*-----������Щ��ɫӵ��ʲôȨ��------*/
INSERT INTO privilege_role (rid,pid) values (1,1);
INSERT INTO privilege_role (rid,pid) values (1,2);
INSERT INTO privilege_role (rid,pid) values (1,3);
INSERT INTO privilege_role (rid,pid) values (1,4);
INSERT INTO privilege_role (rid,pid) values (1,5);
INSERT INTO privilege_role (rid,pid) values (1,6);
INSERT INTO privilege_role (rid,pid) values (1,7);
INSERT INTO privilege_role (rid,pid) values (1,8);
INSERT INTO privilege_role (rid,pid) values (1,9);
INSERT INTO privilege_role (rid,pid) values (1,10);
INSERT INTO privilege_role (rid,pid) values (1,11);
INSERT INTO privilege_role (rid,pid) values (1,12);
INSERT INTO privilege_role (rid,pid) values (1,13);
INSERT INTO privilege_role (rid,pid) values (1,14);
INSERT INTO privilege_role (rid,pid) values (1,15);
INSERT INTO privilege_role (rid,pid) values (1,16);
INSERT INTO privilege_role (rid,pid) values (1,17);
INSERT INTO privilege_role (rid,pid) values (1,18);
INSERT INTO privilege_role (rid,pid) values (1,19);
INSERT INTO privilege_role (rid,pid) values (1,20);
INSERT INTO privilege_role (rid,pid) values (1,21);

SELECT * FROM privilege;
SELECT * FROM account;
SELECT * FROM url;
SELECT * FROM role;
SELECT * FROM privilege_role;
SELECT * FROM account_role;
