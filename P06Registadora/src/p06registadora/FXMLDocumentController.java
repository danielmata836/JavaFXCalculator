package p06registadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pt.ubi.ihc.Registadora;
import static pt.ubi.ihc.Registadora.OPERADORES;

/**
 *
 * @author Daniel
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button but7;
    @FXML
    private Label labelresultado;
    @FXML
    private TextArea textArea;
    @FXML
    private Button butResultado;
    @FXML
    private Button but8;
    @FXML
    private Button but9;
    @FXML
    private Button but4;
    @FXML
    private Button but5;
    @FXML
    private Button but6;
    @FXML
    private Button butMais;
    @FXML
    private Button butMen;
    @FXML
    private Button but1;
    @FXML
    private Button but2;
    @FXML
    private Button but3;
    @FXML
    private Button butMul;
    @FXML
    private Button but0;
    @FXML
    private Button bitPoint;
    @FXML
    private Button butC;
    @FXML
    private Button butDiv;

    //Calculadora métodos e instâncias
    //tem todas as operações efetuadas desde o início da calculadora, 
    //sob a forma de string --> serve principalmente para imprimir no 
    //textArea todo o histórico de operações.
    private StringBuilder buffer = new StringBuilder();
    //aux permite declarar um buffer para cada vez que se adiconam números
    //e permitir escrever números com vários algarismos
    //adiciona números até que se clique num operador. Aí regista a sequência de 
    //algarismos e volta a criar um novo buffer vazio 
    private StringBuilder aux = new StringBuilder();

    private Registadora register = new Registadora();
    @FXML
    private SplitPane splitPane;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == but0) {
            buffer.append("0");
            aux.append("0");
        }

        if (event.getSource() == but1) {
            buffer.append("1");
            aux.append("1");
        }

        if (event.getSource() == but2) {
            buffer.append("2");
            aux.append("2");
        }

        if (event.getSource() == but3) {
            buffer.append("3");
            aux.append("3");
        }

        if (event.getSource() == but4) {
            buffer.append("4");
            aux.append("4");
        }

        if (event.getSource() == but5) {
            buffer.append("5");
            aux.append("5");
        }

        if (event.getSource() == but6) {
            buffer.append("6");
            aux.append("6");
        }

        if (event.getSource() == but7) {
            buffer.append("7");
            aux.append("7");
        }

        if (event.getSource() == but8) {
            buffer.append("8");
            aux.append("8");
        }

        if (event.getSource() == but9) {
            buffer.append("9");
            aux.append("9");
        }

        if (event.getSource() == bitPoint && aux.length() > 0) {
            buffer.append(".");
            aux.append(".");

        }

        if (event.getSource() == butC) {
            buffer.delete(0, buffer.length());
            labelresultado.setText(String.valueOf(0.0));
            register = new Registadora();
            aux = new StringBuilder();
        }

        if (event.getSource() == butMais) {
            //labelresultado.setText(String.valueOf(register.getResultado()));

            //para números com vários algarismos
            register.regista(Double.valueOf(aux.toString()));

            char c = buffer.charAt(buffer.length() - 1);

            //para não concatenar + consecutivos
            if (c != '+') {
                buffer.append(" +\n");//\n
                register.defineOperador('+');
            }
            aux = new StringBuilder();
        }

        if (event.getSource() == butDiv) {
            //labelresultado.setText(String.valueOf(register.getResultado()));

            //para números com vários algarismos
            register.regista(Double.valueOf(aux.toString()));

            char c = buffer.charAt(buffer.length() - 1);

            //para não concatenar + consecutivos
            if (c != '/') {
                buffer.append(" /\n");//\n
                register.defineOperador('/');
            }
            aux = new StringBuilder();
        }

        if (event.getSource() == butMul) {
            //labelresultado.setText(String.valueOf(register.getResultado()));

            //para números com vários algarismos
            register.regista(Double.valueOf(aux.toString()));

            char c = buffer.charAt(buffer.length() - 1);
            //para não concatenar + consecutivos
            if (c != 'x') {
                buffer.append(" x\n");//\n
                register.defineOperador('x');
            }
            aux = new StringBuilder();
        }

        if (event.getSource() == butMen) {
            //labelresultado.setText(String.valueOf(register.getResultado()));
            if(!aux.equals("")){
            //para números com vários algarismos
            register.regista(Double.valueOf(aux.toString()));

            char c = buffer.charAt(buffer.length() - 1);
            //para não concatenar + consecutivos
            if (c != '-') {
                buffer.append(" -\n");//\n
                register.defineOperador('-');
            }
            aux = new StringBuilder();
            }
        }
        //definir o texto da textArea durante o cálculo
        textArea.setText(buffer.toString());

        if (event.getSource() == butResultado) {
            //o if regista o último valor inserido, caso este seja diferente de um operador
            //este if serve para que 2 + 2 + resulta não dê uma exceção. Assim como o 
            //ultimo valro inserido foi um , este valor não é registado na calculadora.
            if (!OPERADORES.contains(aux)) {
                register.regista(Double.valueOf(aux.toString()));
            }
            labelresultado.setText(String.valueOf(register.getResultado()));
            buffer.append("\n--------------------------------" + "\n" + String.valueOf(register.getResultado()) + "\n\n");
            textArea.setText(buffer.toString());
            register = new Registadora();
            aux = new StringBuilder();
        }

        //definir o texto da textArea ao clicar em C
        if (event.getSource() != butC && event.getSource() != butResultado) {
            labelresultado.setText(String.valueOf(register.getResultado()));
        }
    }

    @Override
    //O foco é predefinido de início para estar no resultado.
    public void initialize(URL url, ResourceBundle rb) {
        //forma de fazer request focus (a mera instrução 
        //butResultado.requestFocus(); não funcionaria)
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                butResultado.requestFocus();
            }
        });
    }

    //Para controlar a calculadora a partir do teclado: chamar os dois métodos
    //(1º pressed, 2º released)
    //  - pressed: requestFocus
    //  - relesed: chamada à função usada para os butons
    //Função para fazer focus nos butões antes de executar
    //para que seja possível ler os keyEvents preciso que haja focus num
    //elemento da cena.
    //função para ler keyevents em todas as teclas que está definida como
    //OnKeyPressed() de cada botão
    
    //a flag serve para evitar que ao clicar numa tecla não programada para a 
    //calculadora, o programa volte a executar para o último request onde ficou 
    boolean flag;

    @FXML
    public void buttonPressed(KeyEvent ev) {
        System.out.println("ola");
        flag=false;

        if (ev.getCode() == KeyCode.DIGIT0 || ev.getCode() == KeyCode.NUMPAD0) {
            but0.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIGIT1 || ev.getCode() == KeyCode.NUMPAD1) {
            but1.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIGIT2 || ev.getCode() == KeyCode.NUMPAD2) {
            but2.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIGIT3 || ev.getCode() == KeyCode.NUMPAD3) {
            but3.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIGIT4 || ev.getCode() == KeyCode.NUMPAD4) {
            but4.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIGIT5 || ev.getCode() == KeyCode.NUMPAD5) {
            but5.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIGIT6 || ev.getCode() == KeyCode.NUMPAD6) {
            but6.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIGIT7 || ev.getCode() == KeyCode.NUMPAD7) {
            but7.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIGIT8 || ev.getCode() == KeyCode.NUMPAD8) {
            but8.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIGIT9 || ev.getCode() == KeyCode.NUMPAD9) {
            but9.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.ADD) {
            butMais.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.SUBTRACT) {
            butMen.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.MULTIPLY) {
            butMul.requestFocus();
            flag = true;
        }

        if (ev.getCode() == KeyCode.DIVIDE) {
            butDiv.requestFocus();
            flag = true;
        }
    }

    @FXML
    private void buttonReleased(KeyEvent event) {
        if (flag == true) {
            ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
            handleButtonAction(ae);
            event.consume();
        }
    }
}
/*
    Há um método handle button que define o comportamento de todos os butões 
    depois dentro dessse método tem de haver forma de distinguir qual o método clicado.
    verificar se o componente que originou o evento era um butão, instanciar um objeto do tipo button e ir buscar o texto desse botão
    process é um método que recebe um char e faz o processamento necessário
    
    os calculos vao ser feitos a medida que o utilizador vai introduzindo números.
    Enquanto o utilizador vai clicando em butões, os numeros vao endo guardadas no formato de string. 
    os numeros e operadores vão sendo concatenados.
    
    Não deve permitir inserir números com 2 pontos (8.5.2 -->não faz sentido)
    - usar strinsBuilder em vez de string
 */
