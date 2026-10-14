/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.martinezgallardopablofinal;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class CalculadoraGraficaController implements Initializable {
    @FXML
    private TextField display;
    
    private double numero=0;
    private String operador="";
    private boolean inicio=true;
    private boolean primerSigno=false;
    
    private final DecimalFormat formato = new DecimalFormat("#,#########,###");

    
    @FXML
    private Button btnCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     display.setText("0");
   }    

    @FXML
    private void pulsarNumero(ActionEvent event) 
    {
     String valor=((Button)event.getSource()).getText();
     if(valor.equals("π")) valor = String.valueOf((double) Math.round(Math.PI*100)/100);
     if(valor.equals("e")) valor = String.valueOf((double) Math.round(Math.E*100)/100);
     
     if (inicio)
      {
       display.setText(valor);
       inicio=false;
      }
     else
      {
       if(display.getText().equals("0")) display.setText(valor);
       else display.setText(display.getText()+valor);
      }
        
    }

    @FXML
    private void pulsarOperacion(ActionEvent event) 
    {
     String op=((Button)event.getSource()).getText();
     
     calcular();
     
     operador=op;
     numero=Double.parseDouble(display.getText());
     inicio=true;
    }
    
    public void calcular()
    {
     if (operador.isEmpty()) return;
     
     double sigNumero=Double.parseDouble(display.getText());
     double resultado=0;
     
     switch ( operador )
     {
         case "+" : resultado=numero+sigNumero; break;   
         case "-" : resultado=numero-sigNumero; break;   
         case "*" : resultado=numero*sigNumero; break;   
         case "/" :
             if(sigNumero==0)
              {
               display.setText("Error, división por cero");
               inicio=true;
               operador="";
               return;
              }
             resultado=numero/sigNumero; break;
             
         case "√" : resultado=Math.sqrt(numero); break;
         case "sin" : resultado=Math.sin(numero); break;
         case "cos" : resultado=Math.cos(numero); break;
         case "tan" : resultado=Math.tan(numero); break;
             
     }
     
     display.setText(String.valueOf(resultado));
     numero=resultado;
        
     
    }

    @FXML
    private void pulsarSigno(ActionEvent event) 
    {
     String salida=display.getText();
     double valor;
     
     if (salida.equals("0")) return;
          
     if (salida.charAt(0)=='-')
     {
      valor=Double.parseDouble(salida.substring(1));
     }
     else
     {
      valor=Double.parseDouble(salida);
      valor=(-1)*valor;
     }   
    
     //System.out.println("Valor : " + valor);
     //salida=String.valueOf(valor);
          
     
     display.setText(String.valueOf(valor));
     
     
    /* if(primerSigno)
     {
      display.setText(salida.substring(0, salida.length()-2));
      primerSigno=false;
     }
     else display.setText(salida);
     */
     
    }

    @FXML
    private void pulsarIgual(ActionEvent event) 
    {
     calcular();
     operador="";
     inicio=true;        
    }


    @FXML
    private void pulsarBorrar(ActionEvent event) 
    {
     display.setText("0");
     numero=0;
     operador="";
     inicio=true;
     primerSigno=true;
    }

    @FXML
    private void pulsarDecimal(ActionEvent event) 
    {
     if (inicio)
     {
      display.setText("0.");
      inicio=false;
      return;
     }
     
     if(!display.getText().contains("."))
       display.setText(display.getText() + ".");
     
    }

    @FXML
    private void cerrarCalcGrafica(ActionEvent event) 
    {
     Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
     stage.close();
    }
}