package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import db.DB;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	@FXML
	private MenuItem menuItemMain;

	public void onMenuItemMain() {
		loadView("/gui/Appointments.fxml");
	}

	@FXML
	private MenuItem menuItemAbout;

	public void onMenuItemAbout() {
		loadView("/gui/About.fxml"); // Passa como parametro o FXML da tela About e suas propriedades para ser
										// executado em LoadView
	}

	@Override
	public void initialize(URL uri, ResourceBundle rg) {
		DB.getConnection();
	}

	public synchronized void loadView(String absoluteName) { // M?todo recebendo a string com o caminho do FXML
		try {

			// Esses c?digos manipulam diretamente a cena principal e o vbox principal da
			// cena em tempo de execu??o:

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); // Inicializa loader de tela
																						// padr?o do java fx no caminho
																						// especificado, onde ser?
																						// criado o Vbox da tela about

			VBox newVBox = loader.load(); // Cria o VBox da tela About, onde ser?o exibidas as opera??es feitas a seguir

			Scene mainScene = Main.getMainScene(); // Pega a scene principal criada na classe principal para que seja
													// exibido na mesma tela. Esse m?todo est?tico precisa ser criado.
													// Se der erro dizendo que a main scene est? vazia, ? porque n?o foi
													// atribu?da a var?avel mainscene criada ? cria??o da tela.
													// mainScene = new Scene(parent);

			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); // Pega o vbox principal (j?
																					// existente) dentro do
																					// scrollpane principal, dentro da
																					// scene principal. O content ? o
																					// conteudo XML dentro do arquivo
																					// MsinView.xml
			Node mainMenu = mainVBox.getChildren().get(0); // Pega o primeiro item dentro do VBox acima (nesse caso o
															// Main Menu que precisa ser preservado).
			mainVBox.getChildren().clear(); // Limpa o VBox principal
			mainVBox.getChildren().add(mainMenu); // Adiciona ao VBOx principal o Main Menu obtido acima, que foi
													// preservado na tela
			mainVBox.getChildren().addAll(newVBox.getChildren()); // Adiciona o conte?do do new vbox com os itens do
																	// VBOx About e o painel acima

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", null, e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
}
