
package loginapp;

import Admin.AdminController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsFXMLController;

/**
 * FXML Controller class
 *
 * @author dangt
 */
public class LoginFXMLController implements Initializable {

    LoginModel loginModel = new LoginModel();
    
    @FXML
    private Label dbstatus;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> comboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if(this.loginModel.isDatabaseConnected()){
            this.dbstatus.setText("Connected to Database");
        }else{
            this.dbstatus.setText("Not Connected to Database");
        }
        this.comboBox.setItems(FXCollections.observableArrayList(option.values()));

    }

    @FXML
    public void Login(ActionEvent event){
        try{
            if(this.loginModel.isLogin(this.userName.getText(),this.password.getText(),((option)this.comboBox.getValue()).toString())){
                this.loginStatus.setText("I already clicked");
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                switch(((option)this.comboBox.getValue()).toString()){
                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }
            }
            else{
                this.loginStatus.setText("Wrong Creditials");
            }
        }catch(Exception localException){
            
        }
    }
    
    public void studentLogin(){
        try{
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/students/students.fxml").openStream());
            
            StudentsFXMLController studentsController = (StudentsFXMLController) loader.getController();
            
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student Dashboard");
            userStage.setResizable(false);
            userStage.show();
            
        }catch (IOException ex){
            ex.printStackTrace();
        }
        
    }
    
    public void adminLogin(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminRoot = (Pane)adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
            
            AdminController adminController = (AdminController)adminLoader.getController();
            Scene adminScene = new Scene(adminRoot);
            adminStage.setScene(adminScene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
}
