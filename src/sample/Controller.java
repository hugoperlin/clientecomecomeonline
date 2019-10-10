package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class Controller {



    @FXML
    private TextField tfHost;

    @FXML
    private TextField tfPorta;

    @FXML
    private TextField tfNome;

    @FXML
    private Circle clStatus;

    @FXML
    private Text txtLast;


    @FXML
    private Button btConecta;

    private Cliente cliente=null;



    public void conecta(){
        try{
            if(cliente==null){
                String host = tfHost.getText();
                int porta = Integer.valueOf(tfPorta.getText());
                String nome = tfNome.getText();


                cliente = new Cliente(host,porta);
                cliente.conectar();
                cliente.setNome(nome);

                ajustaComponentes(cliente==null);

            }else{

                cliente.desconectar();

                cliente=null;

                ajustaComponentes(cliente==null);


            }
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    public void mover(ActionEvent evt){
        try{
            Button bt = (Button) evt.getSource();

            if(bt.getText().equals("W")){
                cliente.processa("Up");
            }else if(bt.getText().equals("S")){
                cliente.processa("Down");
            }else if(bt.getText().equals("A")){
                cliente.processa("Left");
            }else{
                cliente.processa("Rigth");
            }


            txtLast.setText(cliente.getUltimaMensagem());


        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.showAndWait();
        }



    }

    private void ajustaComponentes(boolean status){
        clStatus.setFill(status?Color.RED:Color.GREEN);

        btConecta.setText(status?"Conectar":"Desconectar");
        tfHost.setDisable(!status);
        tfPorta.setDisable(!status);
        tfNome.setDisable(!status);
    }


}
