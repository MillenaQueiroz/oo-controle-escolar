package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateStudentGUI extends JFrame {

    private JTextField inputName;
    private JButton confirmButton;
    private JTextField inputCpf;
    private JTextField inputEmail;
    private JTextField inputSenha;
    private JTextField inputEndereco;
    private JTextField inputResponsavel;
    private JTextField inputDtNasc;
    private JButton cleanButton;
    private JPanel cadastroAPanel;

    public CreateStudentGUI() {
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputCpf.setText("");
                inputDtNasc.setText("");
                inputEndereco.setText("");
                inputEmail.setText("");
                inputName.setText("");
                inputResponsavel.setText("");
                inputSenha.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastrar Aluno");
        frame.setContentPane(new CreateStudentGUI().cadastroAPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
