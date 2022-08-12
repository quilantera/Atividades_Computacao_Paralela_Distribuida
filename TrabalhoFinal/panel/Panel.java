package panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import client.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.ComponentOrientation;
import java.awt.BorderLayout;

public class Panel{
     public static JTextArea showClient;
     public static void main(String args[]){
        String[] operations = {"Potencia","MDC","MMC"}; 
        Client client = new Client();
        JFrame window = new JFrame("Trabalho de CDP ");
                window.setSize(600,500);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setLayout(new BorderLayout());
                window.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JButton btnExecute = new JButton("Executar");
        JLabel text1 = new JLabel("Inserir operacoes");
                text1.setBounds(300,20,100,20);
        JLabel num1 = new JLabel("Numero 1");
                text1.setBounds(100,90,75,20);
        JTextField txtNum1 = new JTextField();
                   txtNum1.setColumns(4);

        JLabel num2 = new JLabel("Numero 2");
        JTextField txtNum2 = new JTextField();
                   txtNum2.setColumns(4);
        JComboBox cbOperation = new JComboBox<>(operations);

        JPanel inputArea = new JPanel();
                inputArea.setBorder(new EmptyBorder(20, 0, 0, 0));
                inputArea.setSize(600,70);
                inputArea.add(num1);
                inputArea.add(txtNum1);
                inputArea.add(num2);
                inputArea.add(txtNum2);
                inputArea.add(cbOperation);
                inputArea.add(btnExecute);

        showClient = new JTextArea(20,25);
                showClient.setEnabled(false);
                showClient.setLineWrap(true);
                JScrollPane scroll = new JScrollPane(showClient);
        JPanel showArea = new JPanel();
                showArea.setBorder(new EmptyBorder(70, 150, 10, 150));
        
                showArea.add(scroll);
        
        window.add(inputArea);
        window.add(showArea);
        window.setVisible(true);

        btnExecute.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
              try{
                    int num1 = Integer.parseInt(txtNum1.getText());
                    int num2 = Integer.parseInt(txtNum2.getText());
                    int choose = cbOperation.getSelectedIndex();
                    client.execute(choose, num1, num2);
                }catch(Exception erro){System.out.println("Erro ao passar os parametros:"+erro);}
               
            }
        });
     }
     public static void showStateClient(String value){
        showClient.append("\n"+value);
     }

}
