-- ALTER SESSION SET "_ORACLE_SCRIPT" = true

CREATE USER c##batata IDENTIFIED BY batata123;
GRANT ALL PRIVILEGES TO c##batata;
ALTER USER c##batata QUOTA UNLIMITED ON USERS;

-- CREATE USER batata IDENTIFIED BY batata123;
-- GRANT CONNECT, RESOURCE TO c##batata;
-- GRANT ALL PRIVILEGES TO batata;
