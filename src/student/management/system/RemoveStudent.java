package student.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;

public class RemoveStudent extends JFrame implements ActionListener{

    Choice choiceSTUID;
    JLabel textName, textPhone, textEmail;
    JButton delete, back;

    RemoveStudent(){

        JLabel label = new JLabel("Student ID");
        label.setBounds(50,50,100,30);
        label.setFont(new Font("Tahoma",Font.BOLD, 15));
        add(label);

        choiceSTUID = new Choice();
        choiceSTUID.setBounds(200,50,150,30);
        add(choiceSTUID);

        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            while(resultSet.next()){
                choiceSTUID.add(resultSet.getString("stuId"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50,100,100,30);
        labelName.setFont(new Font("Tahoma",Font.BOLD, 15));
        add(labelName);

        textName = new JLabel();
        textName.setBounds(200,100,100,30);
        add(textName);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50,150,100,30);
        labelPhone.setFont(new Font("Tahoma",Font.BOLD, 15));
        add(labelPhone);

        textPhone = new JLabel();
        textPhone.setBounds(200,150,100,30);
        add(textPhone);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(50,200,100,30);
        labelEmail.setFont(new Font("Tahoma",Font.BOLD, 15));
        add(labelEmail);

        textEmail = new JLabel();
        textEmail.setBounds(200,200,100,30);
        add(textEmail);

        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student where stuId = '"+choiceSTUID.getSelectedItem()+"'");
            while(resultSet.next()){
                textName.setText(resultSet.getString("name"));
                textPhone.setText(resultSet.getString("phone"));
                textEmail.setText(resultSet.getString("email"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        choiceSTUID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery("select * from student where stuId = '"+choiceSTUID.getSelectedItem()+"'");
                    while(resultSet.next()){
                        textName.setText(resultSet.getString("name"));
                        textPhone.setText(resultSet.getString("phone"));
                        textEmail.setText(resultSet.getString("email"));
                    }
                } catch(Exception E) {
                    E.printStackTrace();
                }
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("BACK");
        back.setBounds(200,300,100,30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(700,80,200,200);
        add(img);

        setSize(1000,400);
        setLocation(300,150);
        setLayout(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()== delete){
            try{
                conn c = new conn();
                String query = "delete from student where stuid = '"+choiceSTUID.getSelectedItem()+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Student Deleted Successfully");
                setVisible(false);
                new main_class();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new RemoveStudent();
    }
}