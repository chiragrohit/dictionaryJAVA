import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import url.*;                   //Package url contains api and parsing code

//APPLET CODE
/*
<applet code = "dictionary.class" height = 900 width = 1400>
</applet>
*/


public class dictionary extends Applet implements ActionListener
{
    Label ldict, lsearch, lmean;
    TextField tsearch;
    TextArea tmean;
    Button bSearch, bClear;

    public void init()
    {
        setLayout(null);

        setBackground(Color.black);

        ldict = new Label ("DICTIONARY");
        ldict.setBounds(720, 50 ,380,50);
        add(ldict);

        lsearch = new Label ("SEARCH >>");
        lsearch.setBounds(230, 150, 100, 35);
        add(lsearch);

        //TextField for search box
        tsearch =  new TextField ();
        tsearch.setBounds(350, 150, 600, 35);
        add(tsearch);
        tsearch.addActionListener(this);

        lmean = new Label ("MEANING >>");
        lmean.setBounds(230, 230, 100, 35);
        add(lmean);

        //TextArea for meaning
        tmean = new TextArea();
        tmean.setBounds(350, 230, 1200, 550);
        add(tmean);


        //SEARCH BUTTON
        bSearch = new Button("GO");
        bSearch.setBounds(1000, 150, 40, 35);
        add(bSearch);
        bSearch.addActionListener(this);


        // CLEAR BUTTON
        bClear = new Button("CLEAR");
        bClear.setBounds(1080, 150, 60, 35);
        add(bClear);
        bClear.addActionListener(this);


        // FONTS
        bSearch.setFont(new Font("Arial", Font.BOLD, 15));
        bSearch.setForeground(Color.green);
        bSearch.setBackground(Color.gray);

        bClear.setFont(new Font("Arial", Font.BOLD, 15));
        bClear.setForeground(Color.green);
        bClear.setBackground(Color.gray);

        ldict.setFont(new Font("MONOSPACED", Font.BOLD+Font.ITALIC, 60));
        ldict.setForeground(Color.green);
        ldict.setBackground(Color.gray);


        tsearch.setFont(new Font("MONOSPACED", Font.BOLD, 20));
        tsearch.setForeground(Color.WHITE);
        tsearch.setBackground(Color.gray);

        lsearch.setFont(new Font("Consolas", Font.PLAIN, 16));
        lsearch.setForeground(Color.green);

        lmean.setFont(new Font("Consolas", Font.PLAIN, 16));
        lmean.setForeground(Color.green);

        tmean.setFont(new Font("Consolas", Font.BOLD, 22));
        tmean.setForeground(Color.green);
        tmean.setBackground(Color.darkGray);

    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == bSearch)
        {
            String getMean = tsearch.getText();         //gets String from search TextField

            url objmean = new url();
            String setMean = objmean.meaning(getMean);      //passes the word to api code
            tmean.setText(setMean);
        }
        if(ae.getSource() == bClear)    // clears search and Mening TextField
        {

            tsearch.setText("");
            tmean.setText("");
        }
    }
}