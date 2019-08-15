CREATE TABLE users(
  id INTEGER,
  username VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  password VARCHAR(255),
  email VARCHAR(255),
  created TIMESTAMP default current_timestamp,
  updated TIMESTAMP default current_timestamp,
  status VARCHAR default 'ACTIVE',

  CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE roles(
  id INTEGER,
  name VARCHAR(255),
  created TIMESTAMP default current_timestamp ,
  updated TIMESTAMP default current_timestamp ,
  status VARCHAR default 'ACTIVE',

  CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE user_roles(
  user_id INTEGER,
  role_id INTEGER,

   CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id)
   REFERENCES users(id)
   ON UPDATE NO ACTION ON DELETE CASCADE,
   CONSTRAINT fk_user_roles_roles FOREIGN KEY (user_id)
   REFERENCES roles(id)
   ON UPDATE NO ACTION ON DELETE CASCADE
);

