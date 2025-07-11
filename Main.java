package uas;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private PetControllers service = new PetControllers();
    private ObservableList<Pet> data = FXCollections.observableArrayList();
    private TableView<Pet> table = new TableView<>();
    private TextField tfNama = new TextField();
    private TextField tfSpesies = new TextField();
    private TextField tfUmur = new TextField();
    private ComboBox<String> cbStatus = new ComboBox<>();
    private Pet selectedPet = null;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("CRUD Pet - JavaFX");

        // Table Columns
        TableColumn<Pet, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setMinWidth(50);

        TableColumn<Pet, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colNama.setMinWidth(150);

        TableColumn<Pet, String> colSpesies = new TableColumn<>("Spesies");
        colSpesies.setCellValueFactory(new PropertyValueFactory<>("spesies"));
        colSpesies.setMinWidth(150);

        TableColumn<Pet, String> colUmur = new TableColumn<>("Umur");
        colUmur.setCellValueFactory(cellData -> {
            int umur = cellData.getValue().getUmur();
            return new SimpleStringProperty(umur + " bulan");
        });
        colUmur.setMinWidth(100);

        TableColumn<Pet, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setMinWidth(120);

        table.getColumns().addAll(colId, colNama, colSpesies, colUmur, colStatus);
        table.setItems(data);

        // Form inputs
        tfNama.setPromptText("Nama");
        tfSpesies.setPromptText("Spesies");
        tfUmur.setPromptText("Umur");

        cbStatus.getItems().addAll("Sehat", "Sakit", "Diadopsi");
        cbStatus.setPromptText("Status");
        cbStatus.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 4; -fx-padding: 2;");

        // Selection listener
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedPet = newVal;
            if (newVal != null) {
                tfNama.setText(newVal.getNama());
                tfSpesies.setText(newVal.getSpesies());
                tfUmur.setText(String.valueOf(newVal.getUmur()));
                cbStatus.setValue(newVal.getStatus());
            }
        });

        // Buttons
        Button btnTambah = new Button("Tambah");
        Button btnUpdate = new Button("Update");
        Button btnHapus = new Button("Hapus");

        btnTambah.setOnAction(e -> {
            try {
                String nama = tfNama.getText().trim();
                String spesies = tfSpesies.getText().trim();
                String umurStr = tfUmur.getText().trim();
                String status = cbStatus.getValue();

                if (nama.isEmpty() || spesies.isEmpty() || umurStr.isEmpty() || status == null) {
                    showError("Semua field wajib diisi dan status harus dipilih.");
                    return;
                }

                int umur = Integer.parseInt(umurStr);

                service.tambahPet(nama, spesies, umur, status);
                refreshTable();
                clearForm();
            } catch (NumberFormatException ex) {
                showError("Umur harus berupa angka.");
            }
        });

        btnUpdate.setOnAction(e -> {
            if (selectedPet != null) {
                try {
                    String namaBaru = tfNama.getText().trim();
                    String spesiesBaru = tfSpesies.getText().trim();
                    String umurStr = tfUmur.getText().trim();
                    String statusBaru = cbStatus.getValue();

                    if (namaBaru.isEmpty() || spesiesBaru.isEmpty() || umurStr.isEmpty() || statusBaru == null) {
                        showError("Semua field wajib diisi dan status harus dipilih.");
                        return;
                    }

                    int umurBaru = Integer.parseInt(umurStr);

                    service.updatePet(selectedPet.getId(), namaBaru, spesiesBaru, umurBaru, statusBaru);
                    refreshTable();
                    clearForm();
                } catch (NumberFormatException ex) {
                    showError("Umur harus berupa angka.");
                }
            }
        });

        btnHapus.setOnAction(e -> {
            if (selectedPet != null) {
                service.hapusPet(selectedPet.getId());
                refreshTable();
                clearForm();
            }
        });

        // Label di bawah field, di atas tabel
        Label title = new Label("Data Hewan Peliharaan");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        HBox formBox = new HBox(10, tfNama, tfSpesies, tfUmur, cbStatus, btnTambah, btnUpdate, btnHapus);
        VBox root = new VBox(10, formBox, title, table);
        root.setPadding(new javafx.geometry.Insets(10));

        stage.setScene(new Scene(root, 1000, 400));
        stage.show();

        refreshTable();
    }

    private void refreshTable() {
        data.setAll(service.getAll());
    }

    private void clearForm() {
        tfNama.clear();
        tfSpesies.clear();
        tfUmur.clear();
        cbStatus.setValue(null);
        table.getSelectionModel().clearSelection();
        selectedPet = null;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
