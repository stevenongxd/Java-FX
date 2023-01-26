module JDBC {
	opens main;
	opens model;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
}