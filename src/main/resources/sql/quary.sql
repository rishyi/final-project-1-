create table customer(
                         c_id varchar(20) primary key,
                         name varchar (20),
                         telephone varchar(20)
);


create table orders(
                       o_id varchar (20) primary key,
                       details varchar(20),
                       date date,
                       c_id varchar(20),
                       foreign key (c_id) references customer (c_id) on update cascade on delete cascade
);

create table empolyee (
                          e_id varchar (20) primary key,
                          name varchar(20),
                          address varchar(20),
                          o_id varchar(20),
                          foreign key (o_id) references orders (o_id) on update cascade on delete cascade
);

create table item(
                     i_id varchar (20) primary key,
                     item_name varchar (20),
                     qty_on_hand varchar(20),
                     details varchar(20)
);

create table shelf(
                      sh_id varchar (20) primary key,
                      category varchar (20),
                      size varchar (20),
                      i_id varchar (20),
                      foreign key (i_id) references item (i_id) on update cascade on delete cascade
);


create table payement(
                         p_id varchar (20) primary key,
                         details varchar(20),
                         price varchar (20),
                         date date,
                         o_id varchar (20),
                         foreign key (o_id) references orders (o_id) on update cascade on delete cascade
);

create table supplier (
                          s_id varchar (20) primary key,
                          name varchar (20),
                          telephone varchar (20),
                          p_id varchar (20),
                          foreign key (p_id) references payement (o_id) on update cascade on delete cascade
);

create table supplierDetails(
                                i_id varchar (20),
                                s_id varchar (20),
                                qty varchar (20),
                                description varchar (20),
                                foreign key (i_id) references item (i_id) on update cascade on delete cascade,
                                foreign key (s_id) references supplier (s_id) on update cascade on delete cascade
);

create table orderDetails(
                             o_id varchar (20),
                             i_id varchar (20),
                             details varchar (20),
                             foreign key (o_id) references orders (o_id) on update cascade on delete cascade,
                             foreign key (i_id) references item (i_id) on update cascade on delete cascade
);



create table users(
                      u_id varchar(20)  primary key,
                      name varchar(20),
                      password varchar(20)
);

INSERT INTO users VALUES ('U001','Ravishka','admin123');

INSERT INTO item VALUES ('I001','RTX3090','15','4GB');
INSERT INTO item VALUES ('I002','RX600','12','8GB');
INSERT INTO item VALUES ('I003','SSD samsung','16','500GB');
INSERT INTO item VALUES ('I004','SSD kingston','22','250GB');
INSERT INTO item VALUES ('I005','HDD seagate','18','1TB');
INSERT INTO item VALUES ('I007','GTX560','30','6GB');
INSERT INTO item VALUES ('I008','Samsung','12','24inch IPS Monitor');
INSERT INTO item VALUES ('I009','Dell','15','32inch Monitor');


INSERT INTO customer VALUES ('C001','Kevin','0763423445');
INSERT INTO customer VALUES ('C002','Supun','0712545887');
INSERT INTO customer VALUES ('C003','Kaizer','077548977');