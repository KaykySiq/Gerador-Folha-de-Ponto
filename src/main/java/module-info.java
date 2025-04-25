module com.ibametro.folhaponto.folhaponto {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.annotation;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens com.ibametro.folhaponto.folhaponto to javafx.fxml;
    exports com.ibametro.folhaponto.folhaponto;
    exports com.ibametro.folhaponto.folhaponto.controller;
    opens com.ibametro.folhaponto.folhaponto.controller to javafx.fxml;

    opens com.ibametro.folhaponto.folhaponto.domain to org.hibernate.orm.core, jakarta.persistence;
    exports com.ibametro.folhaponto.folhaponto.service;
    opens com.ibametro.folhaponto.folhaponto.service to javafx.fxml;

}