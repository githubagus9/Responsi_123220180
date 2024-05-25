/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class LoginPage extends JFrame implements ActionListener {
    JLabel headerLogin = new JLabel("Selamat Datang !");
    JLabel tulisan = new JLabel("Silahkan masuk untuk melanjutkan.");
    JLabel username = new JLabel("Username");
    JLabel password = new JLabel("Password");
    JTextField inputUsername = new JTextField();
    JPasswordField inputPassword = new JPasswordField();
    JButton login = new JButton("LOGIN");

    LoginPage() {
        setVisible(true);
        setSize(500, 300);
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        add(headerLogin);
        headerLogin.setFont(new Font("Arial", Font.BOLD, 20));
        headerLogin.setBounds(25, 5, 170, 35);

        add(tulisan);
        tulisan.setFont(new Font("Arial", Font.BOLD, 15));
        tulisan.setBounds(25, 25, 250, 35);

        add(username);
        username.setBounds(25, 60, 150, 35);

        add(inputUsername);
        inputUsername.setBounds(25, 90, 435, 25);

        add(password);
        password.setBounds(25, 120, 150, 35);

        add(inputPassword);
        inputPassword.setBounds(25, 150, 435, 25);

        add(login);
        login.addActionListener(this);
        login.setBounds(40, 200, 400, 35);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String username = inputUsername.getText();
            String password = inputPassword.getText();

            if (username.isEmpty()) {
                throw new Exception("Diharapkan login terlebih dahulu !");
            }
            if (password.isEmpty()) {
                throw new Exception("Password tidak boleh kosong!");
            }
            new MenuPage(username);
            this.dispose();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());

        }
    }
}

class MenuPage extends JFrame implements ActionListener {
    JLabel headerUtama = new JLabel();
    JLabel perintah = new JLabel("Silahkan pilih data yang ingin dilihat.");
    JButton mahasiswa = new JButton("Mahasiswa");
    JButton dosen = new JButton("Dosen");
    JButton logout = new JButton("LOGOUT");

    MenuPage(String username) {
        setVisible(true);
        setSize(500, 250);
        setTitle("Menu Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        add(headerUtama);
        headerUtama.setText("Welcome, " + username);
        headerUtama.setFont(new Font("Arial", Font.BOLD, 20));
        headerUtama.setBounds(25, 5, 310, 35);

        add(perintah);
        perintah.setFont(new Font("Arial", Font.BOLD, 15));
        perintah.setBounds(25, 30, 450, 35);

        add(mahasiswa);
        mahasiswa.setBounds(25, 80, 430, 35);
        add(dosen);
        dosen.setBounds(25, 120, 430, 35);
        add(logout);
        logout.setBounds(25, 160, 430, 35);

        mahasiswa.addActionListener(this);
        dosen.addActionListener(this);
        logout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mahasiswa) {
            new Model.Mahasiswa.ModelTable();
        } else if (e.getSource() == dosen) {
            JOptionPane.showMessageDialog(this, "");
        } else if (e.getSource() == logout) {
            dispose(); // Tutup halaman utama saat logout
            new LoginPage(); // Tampilkan halaman login lagi setelah logout
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
    }
}
