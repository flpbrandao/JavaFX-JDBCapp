package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {
	@FXML
	private MenuItem menuItemMain;
	
	public void onMenuItemMain() {
		System.out.println("Main menu");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rg) {

	}

}
