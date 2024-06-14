 insert into account_tbl(loginId,password,role)values('admin','admin',1);
 insert into account_tbl(loginId,password,role)values('student','student',2);
 
 insert into address_tbl(city,township,street)values('Mandalay','aungmyaytharzan','22st');
 insert into address_tbl(city,township,street)values('Mandalay','patheingyi','kantharyar block');
 insert into address_tbl(city,township,street)values('Yangon','kyaukmyaung','thida street');
 
 insert into student_tbl(name,dob,nrc,image,religion,schoolInfo_id,parent_id,address_id)values('andrew','1991-05-22','9/pamana(n)765642','cat_img.jpeg','burma/buddha',null,null,null);
 insert into student_tbl(name,dob,nrc,image,religion,schoolInfo_id,parent_id,address_id)values('william','1995-03-11','9/wakana(n)55432','dog_img.jpeg','burma/buddha',null,null,null);
 insert into student_tbl(name,dob,nrc,image,religion,schoolInfo_id,parent_id,address_id)values('john','1998-01-25','9/amaza(n)222121','panda_img.jpeg','burma/buddha',null,null,null);
 
 insert into contact_tbl(id,email,primaryContact,secondaryContact)values(1,'andrew@gmail.com','0998887444','0998887744');
 insert into contact_tbl(id,email,primaryContact,secondaryContact)values(2,'william@gmail.com','099998823','097882332');
 insert into contact_tbl(id,email,primaryContact,secondaryContact)values(3,'john@gmail.com','099110222','0998887744');
 
 insert into parent_tbl(id,fatherName,motherName,fatherNrc,motherNrc)values(1,'joeseaph','malarwii','5/pakaka(n)982342','6/yatata(F)0942342');
 insert into parent_tbl(id,fatherName,motherName,fatherNrc,motherNrc)values(2,'soeudum','kara','6/pakaka(n)223242','6/yatata(F)0455342');
 insert into parent_tbl(id,fatherName,motherName,fatherNrc,motherNrc)values(3,'sandra','kaythi','7/wanapa(n)322122','4/samasa(F)9882342');
 
 insert into school_info_tbl(id,rollNum,totalMarks) values (1,10,405);
 insert into school_info_tbl(id,rollNum,totalMarks) values (2,22,380);
 insert into school_info_tbl(id,rollNum,totalMarks) values (3,5,455);
 
update student_tbl set schoolInfo_id =1 ,parent_id =1, address_id =1  where id = 1;
update student_tbl set schoolInfo_id =2 ,parent_id =2, address_id =2  where id = 2;
update student_tbl set schoolInfo_id =3 ,parent_id =3, address_id =3  where id = 3;

insert into university_tbl(student_id,openYear,rollNumber,uniYear,major) values (1,2022,10,'FIRST','JST');
insert into university_tbl(student_id,openYear,rollNumber,uniYear,major) values (1,2023,22,'SECOND','JST');
insert into university_tbl(student_id,openYear,rollNumber,uniYear,major) values (1,2024,11,'THIRD','JST');
insert into university_tbl(student_id,openYear,rollNumber,uniYear,major) values (2,2022,10,'FIRST','ECE');
insert into university_tbl(student_id,openYear,rollNumber,uniYear,major) values (2,2023,22,'SECOND','ECE');
insert into university_tbl(student_id,openYear,rollNumber,uniYear,major) values (2,2024,11,'THIRD','ECE');

 
 