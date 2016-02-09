			**************************************************
			*Metainformation Collector for relation data base*
			**************************************************


FUNCTIONAL
Metainformation Collector for relation data base afford metainformation aboult data base structure(SQLite è Postgres ):
- aboult existing tables of data base.
- aboult fields belong to tables , data type, default value.
- aboult primary keys values.
- aboult foreign keys(include referenced fields and tables).
- aboult created indexes,indexes psevdonims , aboult fields include in index, aboult index unique.  


CONFIGURATION:
Entry point of project: com.qsci.database.metadata.Run.
Data base configurated in: resources/data.properties by following:
-for SQLITE data bases:

	1 Designate in field "url" path to data base 
	2.Value of "login" and "password" set by null .
		EXAMPLE:
		url = jdbc:sqlite:mySQLiteBASE.sl3
		login = null
		password = null

-for POSTGRES data bases:
	 
	Designate in field "url" path to data base
	2.Set "password" value
	3.Set "login" value.
		EXAMPLE: 
		url = jdbc:sqlite:myPostgresBASE.sl3
		login = postgresql
		password = password


DEFAULT DATA BASE: SQLITE Northwind.sl3.

In plans add support by mySQL and DERBY.


