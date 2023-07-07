module com.calculator_for_roboskeletron {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
            
    opens com.calculator_for_roboskeletron to javafx.fxml;
    exports com.calculator_for_roboskeletron;
}