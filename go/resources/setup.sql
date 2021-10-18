CREATE DATABASE IF NOT EXISTS transactions;
USE transactions;

DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS transaction;

CREATE TABLE account(
	id int auto_increment primary key,
	document_number varchar(25) not null unique,
	created_at timestamp default current_timestamp(),
    updated_at timestamp default current_timestamp()
) ENGINE=INNODB;

CREATE TABLE transaction(
    id int auto_increment primary key,
    operation_type int not null,
    amount float not null,
    account_id int not null, FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE,
    created_at timestamp default current_timestamp(),
    updated_at timestamp default current_timestamp()
) ENGINE=INNODB;