package window;

import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.Format;

import window.rechner.Rechner;

public class Window extends JFrame {
    //public static boolean RIGHT_TO_LEFT = false;

    Rechner[] days;
    int day;
    int stat;
    JLabel text;
    JTextField begin;
    JTextField ende;
    JButton next;
    JButton back;
    JButton erg;
    JPanel buttons;
    JPanel eing;
    JPanel label;
    String[] aus;
    int gesammt_stunden;
    int gesammt_min;

    public Window()
    {
        setName("Arbeits Zeiten Rechnerzjb");
        days = new Rechner[31];
        gesammt_stunden = 0;
        gesammt_min = 0;
        day = 0;
        stat = 0;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aus = new String[33];

       // setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
        setLayout(new BorderLayout());
        text = new JLabel("Begin    Ende    day : "+ Integer.toString(stat +1));
        label = new JPanel(new GridBagLayout());

        label.add(text, 0);
        eing = new JPanel(new FlowLayout());
        begin = new JTextField("",15);
        begin.setPreferredSize(new Dimension( 20, 20));

        ende = new JTextField("", 15);
        ende.setPreferredSize(new Dimension(20, 20));
        eing.add(begin);
        eing.add(ende);
        buttons = new JPanel(new FlowLayout());
        next = new JButton("Next");
        back = new JButton("Back");
        erg = new JButton("Ergebnis");

        buttons.add(next);
        buttons.add(back);
        buttons.add(erg);
       // setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);



        add(label, BorderLayout.NORTH);
        add(eing, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        next.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Next();
            }
        });
        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Back();
            }
        });
        erg.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Erg();
            }
        });

        //setSize(500, 200);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void Erg()
    {
        aus[0] = "day   begin   ende   Studen   Hundertstelstunden                                                                     ";
        for(int i = 1; i < day +1 ; i++)
        {
            aus[i] = Integer.toString(i)+"   "+days[i-1].get_Value(0)+"   "+days[i-1].get_Value(1)+"   "+days[i-1].get_Value(2)+"   "+days[i-1].get_Value(3)+  "\n ";
        }

        if(gesammt_min < 10)
        {
            aus[day+1]  = " Gesammt Zeit : " +Integer.toString(gesammt_stunden) + ",0" + Integer.toString(gesammt_min);
        } else if (gesammt_min > 9) {
            aus[day+1]  =" Gesammt Zeit : " + Integer.toString(gesammt_stunden) + "," + Integer.toString(gesammt_min);
        }

        setLayout(new GridLayout(33, 2));
        String ergebnis = "";

        for (int i = 0; i<day+2; i++)
        {

        add(new JLabel(String.valueOf(aus[i])));

        }



        pack();

        back.setVisible(false);
        begin.setVisible(false);
        ende.setVisible(false);
        next.setVisible(false);
        erg.setVisible(false);
        text.setVisible(false);

        setExtendedState(JFrame.MAXIMIZED_BOTH);


    }
    public void Back()
    {
        if(stat > 0)
        {
            stat= stat -1;
            text.setText("Begin    Ende    day : "+ Integer.toString(stat +1));
            begin.setText(days[stat].get_Value(0));
            ende.setText(days[stat].get_Value(1));
        }
    }
    public void Next()
    {
        if(stat >= 30)
        {
            next.setVisible(false);

        }

        String b =  begin.getText();
        String e = ende.getText();


        days[stat] = new Rechner(b, e);
        gesammt_min = gesammt_min + days[stat].get_Time()[1];
        gesammt_stunden = gesammt_stunden + days[stat].get_Time()[0];
        if(gesammt_min > 59)
        {
            gesammt_stunden = gesammt_stunden +1;
            gesammt_min = gesammt_min - 60;
        }

        if(day == stat)
        {
            day = day +1;
        }
        stat = stat + 1;
        text.setText("Begin    Ende    day : "+ Integer.toString(stat +1));

        ende.setText("");
        begin.setText("");
    }
}
