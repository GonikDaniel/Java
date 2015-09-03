package games.BarChart;

/**
 * Created by Daniel_Gonik on 05.08.15.
 */

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BarChartPlot extends JPanel {
    private ArrayList<Integer> values;
    private ArrayList<Character> letters;
    private String title;

    public BarChartPlot(ArrayList<Integer> v, ArrayList<Character> n, String t) {
        values = v;
        letters = n;
        title = t;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null || values.size() == 0)
            return;

        //looking for max & min in the values to sort them later
        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < values.size(); i++) {
            if (minValue > values.get(i))
                minValue = values.get(i);
            if (maxValue < values.get(i))
                maxValue = values.get(i);
        }

        //detecting sizes and proportion based on nums of columns & size of frame
        Dimension d = getSize();
        int clientWidth = d.width;
        int clientHeight = d.height;
        int barWidth = clientWidth / values.size();

        //fonts params
        Font titleFont = new Font("Arial", Font.BOLD, 18);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("Arial", Font.PLAIN, 12);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        //draw titles Strings
        int titleWidth = titleFontMetrics.stringWidth(title);
        int y = titleFontMetrics.getAscent();
        int x = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, x, y);

        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue)
            return;
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        y = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);

        //drawing chart columns
        for (int i = 0; i < values.size(); i++) {
            int valueX = i * barWidth + 1;
            int valueY = top;
            int height = (int) (values.get(i) * scale);

            //we can consider even negative values
            if (values.get(i) >= 0)
                valueY += (int) ((maxValue - values.get(i)) * scale);
            else {
                valueY += (int) (maxValue * scale);
                height = -height;
            }

            //random generator for different color of columns
            Random rand = new Random();
            // Java 'Color' class takes 3 floats, from 0 to 1.
            float red = rand.nextFloat();
            float green = rand.nextFloat();
            float blue = rand.nextFloat();
            Color randomColor = new Color(red, green, blue);
            g.setColor(randomColor);

            //filling rectangle
            g.fillRect(valueX, valueY, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueX, valueY, barWidth - 2, height);

            //titles for columns
            int labelWidth = labelFontMetrics.stringWidth(letters.get(i).toString());
            x = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(letters.get(i).toString(), x, y);
            g.drawString(values.get(i).toString(), x - 2, valueY - 8);
//            System.out.println("valueX" + valueX + " valueY " + valueY + " x " + x + " height " + height);
        }
    }

//    private static String filePath = String.valueOf(BarChartPlot.class.getResource("sample.txt"));
    public static void main(String[] argv) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(700, 700);
        f.setLocationRelativeTo(null);
        Map<Character, Integer> map = countAlphabetFreq("/Users/Daniel_Gonik/IdeaProjects/GUIGUI/src/games/BarChart/sample.txt");
//        Map<Character, Integer> map = countAlphabetFreq(filePath);
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<Character> letters = new ArrayList<>();
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> pair = iterator.next();
            char letter = pair.getKey();
            int count = pair.getValue();
            letters.add(letter);
            values.add(count);
        }


        f.getContentPane().add(new BarChartPlot(values, letters, "Alphabets frequency"));
        f.setVisible(true);

    }

    public static Map<Character, Integer> countAlphabetFreq(String fileName){
        ArrayList<Character> list = readFromFile(fileName);
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            char c = list.get(i);
            if (map.containsKey(c)) map.put(c, map.get(c) + 1);
            else map.put(c, 1);
        }

        return map;
    }

    public static ArrayList<Character> readFromFile(String fileName) {
        String file = fileName;
        file = file.toLowerCase();
        ArrayList<Character> textInChars = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            // читаем посимвольно
            int c;
            while ((c = reader.read()) != -1) {
                //TODO optimize it - maybe some regexp?
                if (c != ' ' && c != '\n' && c != '\t' && c != ',' && c != '.' && c != ':' && c != '\\' && c != ';' && c != '"' && c != '\'')
                    textInChars.add((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return textInChars;
    }
}