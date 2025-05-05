module com.ibametro.folhaponto.folhaponto {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.annotation;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.apache.poi.poi;
    requires java.desktop;
    requires org.json;


    opens com.ibametro.folhaponto.folhaponto to javafx.fxml;
    exports com.ibametro.folhaponto.folhaponto;
    exports com.ibametro.folhaponto.folhaponto.controller;
    opens com.ibametro.folhaponto.folhaponto.controller to javafx.fxml;

    opens com.ibametro.folhaponto.folhaponto.domain to org.hibernate.orm.core, jakarta.persistence, javafx.base;
    exports com.ibametro.folhaponto.folhaponto.services;
    opens com.ibametro.folhaponto.folhaponto.services to javafx.fxml;


}