create table bikes (
    id bigint auto_increment primary key,
    serial_number varchar(30) not null unique,
    is_taken boolean not null
);

create table users (
  id bigint auto_increment primary key,
  username varchar(30) not null unique ,
  password varchar(64) not null ,
  email varchar(20) unique not null,
  is_expired boolean,
  is_locked boolean ,
  is_credentials_expired boolean ,
  is_enabled boolean
);

create table roles (
    id bigint auto_increment primary key ,
    role varchar(20) unique not null
);

create table user_roles (
    id_user bigint not null ,
    id_role bigint not null ,

    foreign key (id_user) references users(id),
    foreign key (id_role) references roles(id),
    primary key (id_user, id_role)
);

create table rentals (
  id bigint auto_increment primary key ,
  id_user bigint not null ,
  id_bike bigint not null ,
  started_at timestamp not null ,
  finished_at timestamp,
  start_latitude varchar(10) not null ,
  start_longitude varchar(10) not null ,
  end_latitude varchar(10),
  end_longitude varchar(10),
  total_price varchar(10),

  foreign key (id_user) references users(id),
  foreign key (id_bike) references bikes(id)
);

create table bike_locations (
  id bigint auto_increment primary key ,
  id_rental bigint,
  id_bike bigint not null ,
  timestamp timestamp not null ,
  latitude varchar(10) not null ,
  longitude varchar(10) not null,

  foreign key (id_rental) references rentals(id),
  foreign key (id_bike) references bikes(id)
);