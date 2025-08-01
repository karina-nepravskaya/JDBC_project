﻿This project is a JDBC application with all CRUD operations (supplemeted by FindById operation) which connects to the Customer table from the dvdrental PostgreSQL Database. Customer table has following attributes: customer_id (serial), store_id (int), first_name (text), last_name (text), email (text), addres_id (int), activebool (bool), create_date (date), last_update (timestamptz), active (int).

model/
- Customer.java is a model of customer object with following attributes: id, first_and last_name, email. There are get() set() methods implemented.

DAO/ (Data Access Object)
- CustomerDao.java is an interface with all methods available: create, find by Id, find all, update, delete customer.
- CustomerDaoImpl.java is implementation of methods placed in the interface. PreparedStatement are used for additional security from SQL injections.

DbUtil.java creates connection with the PostgreSQL DataBase.

App.java is an entry point for the application. Interactive CLI with options for user to perform: create / read / find / update / delete / exit.

Technologies used:
- Java 21
- PosgreSQL
- JDBC
- Maven: build automation tool which manages dependencies and all necessary libraries (JDBC Postgres driver).
