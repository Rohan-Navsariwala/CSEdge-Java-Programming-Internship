import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
// import java.time.*;

class resetpassword extends JPanel implements ActionListener {

    JPasswordField t2 = new JPasswordField();
    JTextField t3 = new JTextField();
    JPasswordField t4 = new JPasswordField();
    JLabel l1 = new JLabel("RESET PASSWORD", SwingConstants.CENTER);
    JLabel l2 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l3 = new JLabel("Password", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("Retype", SwingConstants.RIGHT);
    JButton x1 = new JButton("Update");
    JButton x2 = new JButton("Refresh");
    JButton x3 = new JButton("Close");

    Font f = new Font("arial", Font.BOLD, 15);

    resetpassword() {
        l1.setBounds(0, 50, 500, 30);
        l2.setBounds(0, 100, 235, 30);
        l3.setBounds(0, 160, 235, 30);
        l4.setBounds(0, 220, 235, 30);
        t2.setBounds(250, 100, 150, 30);
        t3.setBounds(250, 160, 150, 30);
        t4.setBounds(250, 220, 150, 30);

        this.setLayout(null);
        this.setBounds(300, 0, 600, 550);
        this.setOpaque(false);
        this.setVisible(true);
        t4.setEditable(true);
        l1.setFont(f);
        l1.setForeground(new Color(0x2C3E50));
        l2.setFont(f);
        l2.setForeground(new Color(0x2C3E50));
        l3.setFont(f);
        l3.setForeground(new Color(0x2C3E50));
        l4.setFont(f);
        l4.setForeground(new Color(0x2C3E50));
        t2.setFont(f);
        t2.setVerifyInputWhenFocusTarget(true);
        t3.setFont(f);
        t4.setFont(f);
        x1.setBounds(70, 280, 150, 30);
        x2.setBounds(250, 280, 150, 30);
        x3.setBounds(160, 340, 150, 30);
        x1.setFont(f);
        x1.setBackground(new Color(0x2C3E50));
        x1.setForeground(new Color(0xffffff));
        x2.setFont(f);
        x2.setBackground(new Color(0x2C3E50));
        x2.setForeground(new Color(0xffffff));
        x3.setFont(f);
        x3.setBackground(new Color(0xC0392B));
        x3.setForeground(new Color(0xffffff));

        add(l1);
        add(l2);
        add(l3);
        add(t2);
        add(t4);
        add(l4);
        add(t3);
        add(x1);
        add(x2);
        add(x3);
        x1.addActionListener(this);
        x2.addActionListener(this);
        x3.addActionListener(this);

    }

    public void actionPerformed(ActionEvent k) {
        if (k.getSource() == x2) {
            t2.setText(null);
            t3.setText(null);
            t4.setText(null);
            JOptionPane.showMessageDialog(null, "Fields refreshed", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

        }
        if (k.getSource() == x3) {
            this.setVisible(false);
            repaint();
        }

        if (k.getSource() == x1) {
            int flag = 0;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root",
                        "1234");

                try {
                    int pass = Integer.parseInt(t2.getText());
                    int temp_pass = Integer.parseInt(t4.getText());
                    long account = Long.parseLong(t3.getText());
                    String sqlretrieve = "SELECT * FROM bank_d WHERE accountno=" + '"' + account + '"';

                    Statement s = con.createStatement();
                    s.execute(sqlretrieve);
                    ResultSet r = s.executeQuery(sqlretrieve);
                    while (r.next()) {
                        if (account == (r.getLong(2)) && temp_pass == pass) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 1) {
                        System.out.println(pass + temp_pass);

                        String sqlupdate = "UPDATE bank_d SET password=? WHERE accountno LIKE ?";
                        PreparedStatement p = con.prepareStatement(sqlupdate);
                        p.setInt(1, pass);
                        p.setLong(2, account);
                        p.executeUpdate();
                        p.close();
                        con.close();

                        JOptionPane.showMessageDialog(this, "Password Updated Successfully", "INFORMATION",
                                JOptionPane.INFORMATION_MESSAGE);
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(this, "User not verified or Mismatch password", "WARNING",
                                JOptionPane.WARNING_MESSAGE);
                        repaint();
                    }

                } catch (Exception k1) {

                    JOptionPane.showMessageDialog(null, "All fields are required", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                    repaint();
                }
            } catch (Exception p) {
                JOptionPane.showMessageDialog(null, p, "WARNING", JOptionPane.WARNING_MESSAGE);
                repaint();
            }
        }

    }
}

class check_balance extends JPanel implements ActionListener {

    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JLabel l1 = new JLabel("CHECK BALANCE", SwingConstants.CENTER);
    JLabel l3 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l2 = new JLabel("Password", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("Cur Balance", SwingConstants.RIGHT);
    JButton x1 = new JButton("Show");
    JButton x2 = new JButton("Refresh");
    JButton x3 = new JButton("Close");

    Font f = new Font("arial", Font.BOLD, 15);

    check_balance() {
        l1.setBounds(0, 50, 500, 30);
        l2.setBounds(0, 100, 235, 30);
        l3.setBounds(0, 160, 235, 30);
        l4.setBounds(0, 220, 235, 30);
        t2.setBounds(250, 100, 150, 30);
        t3.setBounds(250, 160, 150, 30);
        t4.setBounds(250, 220, 150, 30);
        this.setLayout(null);
        this.setBounds(300, 0, 600, 550);
        this.setOpaque(false);
        this.setVisible(true);
        t4.setEditable(false);
        l1.setFont(f);
        l1.setForeground(new Color(0x2C3E50));
        l2.setFont(f);
        l2.setForeground(new Color(0x2C3E50));
        l3.setFont(f);
        l3.setForeground(new Color(0x2C3E50));
        l4.setFont(f);
        l4.setForeground(new Color(0x2C3E50));
        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);
        x1.setBounds(70, 280, 150, 30);
        x2.setBounds(250, 280, 150, 30);
        x3.setBounds(160, 340, 150, 30);
        x1.setFont(f);
        x1.setBackground(new Color(0x2C3E50));
        x1.setForeground(new Color(0xffffff));
        x2.setFont(f);
        x2.setBackground(new Color(0x2C3E50));
        x2.setForeground(new Color(0xffffff));
        x3.setFont(f);
        x3.setBackground(new Color(0xC0392B));
        x3.setForeground(new Color(0xffffff));

        add(l1);
        add(l2);
        add(l3);
        add(t2);
        add(t4);
        add(l4);
        add(t3);
        add(x1);
        add(x2);
        add(x3);
        x1.addActionListener(this);
        x2.addActionListener(this);
        x3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent k) {
        if (k.getSource() == x2) {
            t2.setText(null);
            t3.setText(null);
            t4.setText(null);
            JOptionPane.showMessageDialog(null, "Fields refreshed", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
        }

        if (k.getSource() == x3) {
            this.setVisible(false);
            repaint();
        }

        if (k.getSource() == x1) {
            int flag = 0;
            Connection con = null;
            PreparedStatement p = null;
            ResultSet r = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");

                try {
                    String account = t3.getText();
                    String password1 = t2.getText(); 
                    int temp_balance = 0;

                    String sqlretrieve = "SELECT * FROM bank_d WHERE accountno=? AND password=?";
                    p = con.prepareStatement(sqlretrieve);
                    p.setString(1, account);
                    p.setString(2, password1);
                    r = p.executeQuery();

                    while (r.next()) {
                        if (account.equals(r.getString("accountno")) && password1.equals(r.getString("password"))) {
                            temp_balance = r.getInt("balance");
                            flag = 1;
                            break;
                        }
                    }

                    if (flag == 1) {
                        t4.setText(String.valueOf(temp_balance));
                        JOptionPane.showMessageDialog(this, "Current Balance Displayed", "INFORMATION",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "User not verified", "WARNING",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number format in account number or password",
                            "WARNING", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "All fields are required", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
            } finally {
                try {
                    if (r != null)
                        r.close();
                    if (p != null)
                        p.close();
                    if (con != null)
                        con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
            repaint();
        }
    }

}

class withdrawal extends JPanel implements ActionListener {

    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JLabel l1 = new JLabel("WITHDRAWAL AMOUNT", SwingConstants.CENTER);
    JLabel l3 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l2 = new JLabel("Withdrawal Amt", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("Cur Balance", SwingConstants.RIGHT);
    JButton x1 = new JButton("Withdrawal");
    JButton x2 = new JButton("Refresh");
    JButton x3 = new JButton("Close");

    Font f = new Font("arial", Font.BOLD, 15);

    withdrawal() {
        l1.setBounds(0, 50, 500, 30);
        l2.setBounds(0, 100, 235, 30);
        l3.setBounds(0, 160, 235, 30);
        l4.setBounds(0, 220, 235, 30);
        t2.setBounds(250, 100, 150, 30);
        t3.setBounds(250, 160, 150, 30);
        t4.setBounds(250, 220, 150, 30);
        this.setLayout(null);
        this.setBounds(300, 0, 600, 550);
        this.setOpaque(false);
        this.setVisible(true);
        t4.setEditable(false);
        l1.setFont(f);
        l1.setForeground(new Color(0x2C3E50));
        l2.setFont(f);
        l2.setForeground(new Color(0x2C3E50));
        l3.setFont(f);
        l3.setForeground(new Color(0x2C3E50));
        l4.setFont(f);
        l4.setForeground(new Color(0x2C3E50));
        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);
        x1.setBounds(70, 280, 150, 30);
        x2.setBounds(250, 280, 150, 30);
        x3.setBounds(160, 340, 150, 30);
        x1.setFont(f);
        x1.setBackground(new Color(0x2C3E50));
        x1.setForeground(new Color(0xffffff));
        x2.setFont(f);
        x2.setBackground(new Color(0x2C3E50));
        x2.setForeground(new Color(0xffffff));
        x3.setFont(f);
        x3.setBackground(new Color(0xC0392B));
        x3.setForeground(new Color(0xffffff));

        add(l1);
        add(l2);
        add(l3);
        add(t2);
        add(t4);
        add(l4);
        add(t3);
        add(x1);
        add(x2);
        add(x3);
        x1.addActionListener(this);
        x2.addActionListener(this);
        x3.addActionListener(this);

    }

    public void actionPerformed(ActionEvent k) {
        if (k.getSource() == x1) {
            int flag = 0;
            Connection con = null;
            Statement s = null;
            PreparedStatement p = null;
            ResultSet r = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");

                try {
                    long account = Long.parseLong(t3.getText());
                    long withdrawalamt = Long.parseLong(t2.getText());
                    long temp_balance = 0;

                    String sqlretrieve = "SELECT * FROM bank_d WHERE accountno=?";
                    p = con.prepareStatement(sqlretrieve);
                    p.setLong(1, account);
                    r = p.executeQuery();

                    while (r.next()) {
                        if (account == r.getLong("accountno")) {
                            temp_balance = r.getLong("balance");
                            flag = 1;
                            break;
                        }
                    }

                    if (flag == 1) {
                        if (temp_balance >= withdrawalamt) {
                            temp_balance -= withdrawalamt;

                            String sqlupdate = "UPDATE bank_d SET balance=? WHERE accountno=?";
                            p = con.prepareStatement(sqlupdate);
                            p.setLong(1, temp_balance);
                            p.setLong(2, account);
                            p.executeUpdate();

                            t4.setText(String.valueOf(temp_balance));
                            JOptionPane.showMessageDialog(this, "Amount Withdrawn Successfully", "INFORMATION",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Insufficient Balance", "WARNING",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Create Account or User not Verified", "WARNING",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number format in account number or withdrawal amount",
                            "WARNING", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "All fields are required", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
            } finally {
                try {
                    if (r != null)
                        r.close();
                    if (p != null)
                        p.close();
                    if (s != null)
                        s.close();
                    if (con != null)
                        con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
            repaint();
        }

        if (k.getSource() == x2) {
            t2.setText(null);
            t3.setText(null);
            t4.setText(null);
            JOptionPane.showMessageDialog(null, "Fields refreshed", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
        }

        if (k.getSource() == x3) {
            this.setVisible(false);
            repaint();
        }
    }

}

class deposit extends JPanel implements ActionListener {

    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JLabel l1 = new JLabel("DEPOSIT AMOUNT", SwingConstants.CENTER);
    JLabel l3 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l2 = new JLabel("Deposit Amt", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("Cur Balance", SwingConstants.RIGHT);
    JButton x1 = new JButton("Deposit");
    JButton x2 = new JButton("Refresh");
    JButton x3 = new JButton("Close");

    Font f = new Font("arial", Font.BOLD, 15);

    deposit() {
        l1.setBounds(0, 50, 500, 30);
        l2.setBounds(0, 100, 235, 30);
        l3.setBounds(0, 160, 235, 30);
        t2.setBounds(250, 100, 150, 30);
        t3.setBounds(250, 160, 150, 30);
        l4.setBounds(0, 220, 235, 30);
        t4.setBounds(250, 220, 150, 30);
        this.setLayout(null);
        this.setBounds(300, 0, 600, 550);
        this.setOpaque(false);
        this.setVisible(true);
        l1.setFont(f);
        l1.setForeground(new Color(0x2C3E50));
        l2.setFont(f);
        l2.setForeground(new Color(0x2C3E50));
        l3.setFont(f);
        l3.setForeground(new Color(0x2C3E50));
        l4.setFont(f);
        l4.setForeground(new Color(0x2C3E50));
        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);
        x1.setBounds(70, 280, 150, 30);
        x2.setBounds(250, 280, 150, 30);
        x3.setBounds(160, 340, 150, 30);
        x1.setFont(f);
        x1.setBackground(new Color(0x2C3E50));
        x1.setForeground(new Color(0xffffff));
        x2.setFont(f);
        x2.setBackground(new Color(0x2C3E50));
        x2.setForeground(new Color(0xffffff));
        x3.setFont(f);
        x3.setBackground(new Color(0xC0392B));
        x3.setForeground(new Color(0xffffff));

        add(l1);
        add(l2);
        add(l3);
        add(t2);
        add(t4);
        add(l4);
        add(t3);
        add(x1);
        add(x2);
        add(x3);
        t4.setEditable(false);
        x1.addActionListener(this);
        x2.addActionListener(this);
        x3.addActionListener(this);

    }

    public void actionPerformed(ActionEvent k) {
        if (k.getSource() == x1) {
            int flag = 0;
            Connection con = null;
            Statement s = null;
            PreparedStatement p = null;
            ResultSet r = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");

                try {
                    long account = Long.parseLong(t3.getText());
                    long deposit = Long.parseLong(t2.getText());
                    long temp_balance = 0;

                    String sqlretrieve = "SELECT * FROM bank_d WHERE accountno=?";
                    s = con.createStatement();
                    p = con.prepareStatement(sqlretrieve);
                    p.setLong(1, account);
                    r = p.executeQuery();

                    while (r.next()) {
                        if (account == r.getLong("accountno")) {
                            temp_balance = r.getLong("balance");
                            flag = 1;
                            break;
                        }
                    }

                    if (flag == 1) {
                        temp_balance += deposit;

                        String sqlupdate = "UPDATE bank_d SET balance=? WHERE accountno=?";
                        p = con.prepareStatement(sqlupdate);
                        p.setLong(1, temp_balance);
                        p.setLong(2, account);
                        p.executeUpdate();

                        t4.setText(String.valueOf(temp_balance));
                        JOptionPane.showMessageDialog(this, "Amount Deposited Successfully", "INFORMATION",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Create Account or User not Verified", "INFORMATION",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number format in account number or deposit amount",
                            "WARNING", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "All fields are required", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
            } finally {
                try {
                    if (r != null)
                        r.close();
                    if (p != null)
                        p.close();
                    if (s != null)
                        s.close();
                    if (con != null)
                        con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
            repaint();
        }

        if (k.getSource() == x2) {
            t2.setText(null);
            t3.setText(null);
            t4.setText(null);
            JOptionPane.showMessageDialog(null, "Fields refreshed", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
        }

        if (k.getSource() == x3) {
            this.setVisible(false);
            repaint();
        }
    }

}

class authenticating extends JPanel implements ActionListener {

    Font f = new Font("arial", Font.BOLD, 15);
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JLabel l1 = new JLabel("USER VERIFICATION", SwingConstants.CENTER);
    JLabel l2 = new JLabel("Customer Name", SwingConstants.RIGHT);
    JLabel l3 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("PanCard No", SwingConstants.RIGHT);
    JButton x1 = new JButton("Verify");
    JButton x2 = new JButton("Refresh");
    JButton x3 = new JButton("Close");

    authenticating() {
        this.setLayout(null);
        this.setBounds(300, 0, 600, 550);
        this.setOpaque(false);
        this.setVisible(true);
        l1.setFont(f);
        l1.setForeground(new Color(0x2C3E50));
        l2.setFont(f);
        l2.setForeground(new Color(0x2C3E50));
        l3.setFont(f);
        l3.setForeground(new Color(0x2C3E50));
        l4.setFont(f);
        l4.setForeground(new Color(0x2C3E50));
        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);

        l1.setBounds(0, 50, 500, 30);
        l2.setBounds(0, 100, 235, 30);
        t2.setBounds(250, 100, 150, 30);
        l3.setBounds(0, 160, 235, 30);
        t3.setBounds(250, 160, 150, 30);
        l4.setBounds(0, 220, 235, 30);
        t4.setBounds(250, 220, 150, 30);
        x1.setBounds(70, 280, 150, 30);
        x2.setBounds(250, 280, 150, 30);
        x3.setBounds(160, 340, 150, 30);
        x1.setFont(f);
        x1.setBackground(new Color(0x2C3E50));
        x1.setForeground(new Color(0xffffff));
        x2.setFont(f);
        x2.setBackground(new Color(0x2C3E50));
        x2.setForeground(new Color(0xffffff));
        x3.setFont(f);
        x3.setBackground(new Color(0xC0392B));
        x3.setForeground(new Color(0xffffff));

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(t2);
        add(t3);
        add(t4);
        add(x1);
        add(x2);
        add(x3);
        x1.addActionListener(this);
        x2.addActionListener(this);
        x3.addActionListener(this);

    }

    public void actionPerformed(ActionEvent k1) {
        if (k1.getSource() == x1) {
            int flag = 0;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root",
                        "1234");

                try {

                    long account = Long.parseLong(t3.getText());
                    String pan = t4.getText().toString();
                    String cname = t2.getText().toString();

                    String sqlretrieve = "SELECT * FROM bank_d WHERE accountno=" + '"' + account + '"'
                            + "AND pancard_no LIKE" + '"' + pan + '"';
                    Statement s = con.createStatement();
                    s.execute(sqlretrieve);
                    ResultSet rs = s.executeQuery(sqlretrieve);
                    while (rs.next()) {
                        if ((rs.getString(1).equals(cname) && rs.getLong(2) == account
                                && rs.getString(3).equals(pan))) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 1) {
                        JOptionPane.showMessageDialog(null, "User is Authenticated", "INFORMATION",
                                JOptionPane.INFORMATION_MESSAGE);
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Create Account", "INFORMATION",
                                JOptionPane.INFORMATION_MESSAGE);
                        repaint();
                    }

                } catch (Exception k) {
                    JOptionPane.showMessageDialog(null, "All fields are required", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                    repaint();
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1, "WARNING", JOptionPane.WARNING_MESSAGE);
                repaint();
            }

        }

        if (k1.getSource() == x2) {
            t2.setText(null);
            t3.setText(null);
            t4.setText(null);
            JOptionPane.showMessageDialog(null, "Fields refreshed", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

            repaint();
        }
        if (k1.getSource() == x3) {
            this.setVisible(false);
            repaint();
        }
    }
}

class user_account extends JPanel implements ActionListener {

    Font f = new Font("arial", Font.BOLD, 15);
    JLabel l1 = new JLabel("ACCOUNT OPENING", SwingConstants.CENTER);
    JLabel l2 = new JLabel("Customer Name", SwingConstants.RIGHT);
    JLabel l3 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("PanCard No", SwingConstants.RIGHT);
    JLabel l5 = new JLabel("Gender:", SwingConstants.RIGHT);
    JLabel l6 = new JLabel("Branch City", SwingConstants.RIGHT);

    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    ButtonGroup group = new ButtonGroup();
    JRadioButton b1 = new JRadioButton("Male");
    JRadioButton b2 = new JRadioButton("Female");
    // JRadioButton b3 = new JRadioButton("Others");
    JButton x1 = new JButton("Create");
    JButton x2 = new JButton("Refresh");
    JButton x3 = new JButton("Close");
    JComboBox c1 = new JComboBox();

    user_account() {
        this.setLayout(null);
        this.setSize(600, 550);
        this.setOpaque(false);
        this.setVisible(true);
        l1.setFont(f);
        l1.setForeground(new Color(0x2C3E50));
        l2.setFont(f);
        l2.setForeground(new Color(0x2C3E50));
        l3.setFont(f);
        l3.setForeground(new Color(0x2C3E50));
        l4.setFont(f);
        l4.setForeground(new Color(0x2C3E50));
        l5.setFont(f);
        l5.setForeground(new Color(0x2C3E50));
        l6.setFont(f);
        l6.setForeground(new Color(0x2C3E50));

        b1.setFont(f);
        b1.setBackground(new Color(0xffffff));
        b1.setForeground(new Color(0x2C3E50));
        b2.setFont(f);
        b2.setBackground(new Color(0xffffff));
        b2.setForeground(new Color(0x2C3E50));

        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);

        group.add(b1);
        group.add(b2);

        c1.addItem("Ahmedabad");
        c1.addItem("Baroda");
        c1.addItem("Rajkot");
        c1.addItem("Surat");
        c1.addItem("Vapi");

        l1.setBounds(0, 50, 500, 30);
        l2.setBounds(0, 100, 230, 30);
        t2.setBounds(250, 100, 150, 30);
        l3.setBounds(0, 160, 230, 30);
        t3.setBounds(250, 160, 150, 30);
        l4.setBounds(0, 220, 230, 30);
        t4.setBounds(250, 220, 150, 30);
        l5.setBounds(0, 280, 230, 30);
        b1.setBounds(250, 280, 70, 30);
        b2.setBounds(325, 280, 80, 30);
        l6.setBounds(0, 340, 230, 30);
        c1.setBounds(250, 340, 160, 30);
        x1.setBounds(70, 400, 150, 30);
        x2.setBounds(260, 400, 150, 30);
        x3.setBounds(160, 460, 150, 30);
        x1.setFont(f);
        x1.setBackground(new Color(0x2C3E50));
        x1.setForeground(new Color(0xffffff));
        x2.setFont(f);
        x2.setBackground(new Color(0x2C3E50));
        x2.setForeground(new Color(0xffffff));
        x3.setFont(f);
        x3.setBackground(new Color(0xC0392B));
        x3.setForeground(new Color(0xffffff));
        c1.setFont(f);
        c1.setBackground(new Color(0xffffff));
        c1.setForeground(new Color(0x2C3E50));
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(t2);
        add(t3);
        add(t4);
        add(l5);
        add(b1);
        add(b2);
        // add(b3);
        add(l6);
        add(c1);
        add(x1);
        add(x2);
        add(x3);
        x1.addActionListener(this);
        x2.addActionListener(this);
        x3.addActionListener(this);

    }

    public void actionPerformed(ActionEvent k) {
        if (k.getSource() == x1) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");
                try {
                    String name = t2.getText();
                    String accno = t3.getText();
                    String panno = t4.getText();
                    String gender = "";
                    if (b1.isSelected()) {
                        gender = "Male";
                    } else if (b2.isSelected()) {
                        gender = "Female";
                    }
                    String city = c1.getSelectedItem().toString();

                    System.out.println(name + accno + panno + gender + city);
                    String sqlinsert = "INSERT INTO bank_d(customername,accountno,pancard_no,gender,city) VALUES(?,?,?,?,?)";
                    PreparedStatement statement = con.prepareStatement(sqlinsert);
                    statement.setString(1, name);
                    statement.setString(2, accno);
                    statement.setString(3, panno);
                    statement.setString(4, gender);
                    statement.setString(5, city);
                    statement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Account created successfully", "INFORMATION",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                } finally {
                    try {
                        con.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage(), "WARNING",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception k1) {
                JOptionPane.showMessageDialog(null, "Error: " + k1.getMessage(), "WARNING",
                        JOptionPane.WARNING_MESSAGE);
            }
            repaint();
        }
        if (k.getSource() == x2) {
            t2.setText(null);
            t3.setText(null);
            t4.setText(null);
            c1.setSelectedIndex(0);
            group.clearSelection();
            repaint();
            JOptionPane.showMessageDialog(null, "Fields are refreshed", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
        }
        if (k.getSource() == x3) {
            this.setVisible(false);
            repaint();
        }
    }

}

class atm extends JFrame implements ActionListener {
    boolean flag = false;

    JLabel l1 = new JLabel("ATM Mgmt System", SwingConstants.CENTER);
    JButton b1 = new JButton("Authenticate");
    JButton b2 = new JButton("Add User");
    JButton b3 = new JButton("Withdraw");
    JButton b4 = new JButton("Deposit");
    JButton b5 = new JButton("Reset Password");
    JButton b6 = new JButton("Check Balance");
    Font f = new Font("arial", Font.BOLD, 13);
    Font f1 = new Font("arial", Font.BOLD, 18);
    JPanel nav = new JPanel();
    Component frame2;

    atm() {
        setLayout(null);
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ATM MANAGEMENT SYSTEM");
        setVisible(true);
        setResizable(false);

        l1.setBounds(0, 50, 300, 30);
        b1.setBounds(60, 150, 180, 32);
        b2.setBounds(60, 200, 180, 32);
        b3.setBounds(60, 250, 180, 32);
        b4.setBounds(60, 300, 180, 32);
        b5.setBounds(60, 350, 180, 32);
        b6.setBounds(60, 400, 180, 32);

        l1.setFont(f1);
        l1.setForeground(Color.WHITE);
        b1.setFont(f);
        b1.setBackground(new Color(0xffffff));
        b1.setForeground(new Color(0x2C3E50));
        b2.setFont(f);
        b2.setBackground(new Color(0xffffff));
        b2.setForeground(new Color(0x2C3E50));
        b3.setFont(f);
        b3.setBackground(new Color(0xffffff));
        b3.setForeground(new Color(0x2C3E50));
        b4.setFont(f);
        b4.setBackground(new Color(0xffffff));
        b4.setForeground(new Color(0x2C3E50));
        b5.setFont(f);
        b5.setBackground(new Color(0xffffff));
        b5.setForeground(new Color(0x2C3E50));
        b6.setFont(f);
        b6.setBackground(new Color(0xffffff));
        b6.setForeground(new Color(0x2C3E50));

        nav.setBackground(new Color(0x2C3E50));
        nav.setLayout(null);
        nav.setBounds(0, 0, 300, 550);
        nav.setVisible(true);
        nav.add(l1);
        nav.add(b1);
        nav.add(b2);
        nav.add(b3);
        nav.add(b4);
        nav.add(b5);
        nav.add(b6);
        add(nav);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b6.addActionListener(this);
        b5.addActionListener(this);

    }

    public void actionPerformed(ActionEvent k1) {

        if (frame2 != null) {
            frame2.setVisible(false);
            repaint();
        }

        if (k1.getSource() == b2) {
            user_account u1 = new user_account();
            addAndRepaint(u1);
        }
        if (k1.getSource() == b5) {
            resetpassword r = new resetpassword();
            addAndRepaint(r);
        }
        if (k1.getSource() == b6) {
            check_balance cb = new check_balance();
            addAndRepaint(cb);
        }
        if (k1.getSource() == b3) {
            withdrawal w1 = new withdrawal();
            addAndRepaint(w1);
        }
        if (k1.getSource() == b4) {
            deposit d1 = new deposit();
            addAndRepaint(d1);
        }
        if (k1.getSource() == b1) {
            authenticating a1 = new authenticating();
            addAndRepaint(a1);
        }
    }

    private void addAndRepaint(Component component) {
        component.setBounds(300, 0, 500, 550);
        add(component);
        repaint();
        frame2 = component;
    }

}

public class Main {
    public static void main(String args[]) {
        atm u = new atm();
    }
}
