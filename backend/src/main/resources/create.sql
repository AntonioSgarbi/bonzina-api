create sequence hibernate_sequence start 1 increment 1;
create table tb_doctor (id int4 not null, clinic int4 not null, email varchar(255), name varchar(255), period int4 not null, phone varchar(255), register int4 not null, speciality varchar(255), primary key (id));
create table tb_patient (id int4 not null, address varchar(255), birthdate date not null, email varchar(255), name varchar(255), phone varchar(255), primary key (id));
create table tb_scheduling (id int4 not null, date date not null, scheduled time not null, doctor_id int4, patient_id int4, primary key (id));
alter table if exists tb_scheduling add constraint FKhku6coeji1b3hatnm7055eb6q foreign key (doctor_id) references tb_doctor;
alter table if exists tb_scheduling add constraint FKirn1ov359on433hjnde9qo31j foreign key (patient_id) references tb_patient;
