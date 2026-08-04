package student.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.sql.*;

public class AddStudent extends JFrame implements ActionListener {

    Random ran = new Random();

    int number = ran.nextInt(999999);

    JTextField tname, tfname, tmname, taddress, tphone, temail, tclass;
    JLabel tstuid;
    JDateChooser tdob;
    JComboBox Boxeducation;
    JRadioButton rbmale, rbfemale, rbother;
    ButtonGroup genderGroup;
    JButton add,back;

    AddStudent(){

        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Add Student Detail");
        heading.setBounds(320,30,500,50);
        heading.setFont(new Font("serif",Font.BOLD,25));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(50,150,150,30);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(name);

        tname = new JTextField();
        tname.setBounds(200,150,150,30);
        tname.setBackground(new Color(177,252,197));
        add(tname);

        JLabel clas = new JLabel("Class");
        clas.setBounds(50,200,150,30);
        clas.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(clas);

        tclass = new JTextField();
        tclass.setBounds(200,200,150,30);
        tclass.setBackground(new Color(177,252,197));
        add(tclass);

        JLabel fname = new JLabel("Father's Name");
        fname.setBounds(400,150,150,30);
        fname.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(fname);

        tfname = new JTextField();
        tfname.setBounds(600,150,150,30);
        tfname.setBackground(new Color(177,252,197));
        add(tfname);

        JLabel mname = new JLabel("Mother's Name");
        mname.setBounds(400,200,150,30);
        mname.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(mname);

        tmname = new JTextField();
        tmname.setBounds(600,200,150,30);
        tmname.setBackground(new Color(177,252,197));
        add(tmname);

        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(50,250,150,30);
        dob.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(200,250,150,30);
        tdob.setBackground(new Color(177,252,197));
        add(tdob);

        JLabel address = new JLabel("Address");
        address.setBounds(400,250,150,30);
        address.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(address);

        taddress = new JTextField();
        taddress.setBounds(600,250,150,30);
        taddress.setBackground(new Color(177,252,197));
        add(taddress);

        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(50,300,150,30);
        phone.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(200,300,150,30);
        tphone.setBackground(new Color(177,252,197));
        add(tphone);

        JLabel email = new JLabel("Email Address");
        email.setBounds(400,300,150,30);
        email.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(email);

        temail = new JTextField();
        temail.setBounds(600,300,150,30);
        temail.setBackground(new Color(177,252,197));
        add(temail);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(50,350,150,30);
        gender.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(gender);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(200,350,80,30);
        rbmale.setBackground(new Color(163,255,188));
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(280,350,90,30);
        rbfemale.setBackground(new Color(163,255,188));
        add(rbfemale);

        rbother = new JRadioButton("Other");
        rbother.setBounds(370,350,80,30);
        rbother.setBackground(new Color(163,255,188));
        add(rbother);

        genderGroup = new ButtonGroup();
        genderGroup.add(rbmale);
        genderGroup.add(rbfemale);
        genderGroup.add(rbother);

        JLabel stuid = new JLabel("Student ID");
        stuid.setBounds(50,450,150,30);
        stuid.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(stuid);

        tstuid = new JLabel(""+number);
        tstuid.setBounds(200,450,150,30);
        tstuid.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        tstuid.setForeground(Color.RED);
        add(tstuid);

        add = new JButton("ADD");
        add.setBounds(250,550,150,40);
        add.setBackground(Color.WHITE);
        add.setForeground(Color.black);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(450,550,150,40);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.black);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setLocation(300,50);
        setSize(800,650);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == add) {
            String name = tname.getText();
            String clas = tclass.getText();
            String fname = tfname.getText();
            String mname = tmname.getText();
            String dob = ((JTextField) tdob.getDateEditor().getUiComponent()).getText();
            String address = taddress.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String gender = "";
            if (rbmale.isSelected()) {
                gender = "Male";
            } else if (rbfemale.isSelected()) {
                gender = "Female";
            } else if (rbother.isSelected()) {
                gender = "Other";
            }
            String stuid = tstuid.getText();

            try{
                conn c = new conn();
                String query = "insert into student values('"+name+"','"+clas+"','"+fname+"','"+mname+"','"+dob+"','"+address+"','"+phone+"','"+email+"', '"+gender+"','"+stuid+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added Successfully");
                new main_class();
            } catch (Exception E) {
                E.printStackTrace();
            }


        }else{
            setVisible(false);
            new main_class();
        }


    }


    public static void main(String[] args){
        new AddStudent();
    }
}