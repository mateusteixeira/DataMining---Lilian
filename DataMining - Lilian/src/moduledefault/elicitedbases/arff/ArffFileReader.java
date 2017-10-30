package moduledefault.elicitedbases.arff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArffFileReader {

    String relation;
    List<String> attributes = new ArrayList<String>();
    List<Object[]> data = new ArrayList<Object[]>();

    /**
     * constructor
     * @param file
     */
    public ArffFileReader(File file) {
        loadRelation(file);
        loadAttributes(file);
        loadData(file);
    }

    /**
     * returns the attributes
     * @return attributes
     */
    public List<String> getAttributes() {
        return attributes;
//        String[] aux = new String[attributes.size()];
//        for (int i= 0; i < attributes.size(); i++) {
//            aux[i]=attributes.get(i);
//        }
//        return aux;
    }

    /**
     * returns data
     * @return aux
     */
    public Object[][] getData() {
        Object[][] aux = new Object[data.size()][attributes.size()];
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < attributes.size(); j++) {
                aux[i][j] = data.get(i)[j];                
            }
        }
        return aux;
    }

    /**
     * returns the relation
     * @return relation
     */
    public String getRelation() {
        return relation;
    }

    /**
     * load relations
     * @param file
     */
    private void loadRelation(File file) {
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (!line.toUpperCase().contains("@RELATION")) {
                line = br.readLine();
            }
            line = line.substring(line.indexOf(" ") + 1);
            line.replace("\"", "");
            line.replace("\'", "");
            relation = line;

        } catch (IOException ex) {
            Logger.getLogger(ArffFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * load attributes
     * @param file
     */
    private void loadAttributes(File file) {
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (!line.toUpperCase().contains("@ATTRIBUTE")) {
                line = br.readLine();
            }

            while (line.toUpperCase().contains("@ATTRIBUTE")) {
                //retira o @atribute
                line = line.substring(line.indexOf(" ") + 1);
                String attribute = "";

                //ver se o nome do atributo esta com aspas
                int caso = 0;
                if (line.charAt(0) == '\'') {
                    caso = 1;
                    line = line.substring(1);
                }
                if (line.charAt(0) == '\"') {
                    caso = 2;
                    line = line.substring(1);
                }

                switch (caso) {
                    case 0: //sem aspas
                        if (line.indexOf("\t") == -1) {
                            attribute = line.substring(0, line.indexOf(" "));
                        } else {
                            attribute = line.substring(0, line.indexOf("\t"));
                        }
                        break;
                    case 1:
                        attribute = line.substring(0, line.indexOf("\'"));
                        break;
                    case 2:
                        attribute = line.substring(0, line.indexOf("\""));
                        break;
                }
                attributes.add(attribute);
                //ignora 'por enquanto'  o restante da linha
                line = br.readLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(ArffFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * load data
     * @param file
     */
    private void loadData(File file) {
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (!line.toUpperCase().contains("@DATA")) {
                line = br.readLine();
            }
            line = br.readLine();
            while (line != null) {
                //se linha não for comentario
                //System.out.println("linha: '"+ line+"'" + line.length());
                if (line.length()>0) {
                    if (line.charAt(0) != '%') {
                        String[] row = line.split(",");
                        //eliminar as aspas dos dados
                        for (int i = 0; i < row.length; i++) {
                            //System.out.println("original row[" + i + "]" + row[i]);
                            if (row[i].startsWith("\'")) {
                                row[i] = row[i].substring(1, row[i].length() - 1);
                            } else {
                                if (row[i].startsWith("\"")) {
                                    row[i] = row[i].substring(1, row[i].length() - 1);
                                }
                            }
                            if(row[i].equals("?")){
                                row[i] = null;
                            }
                           // System.out.println("formated row[" + i + "]" + row[i]);
                        }
                        data.add(row);
                    }
                }
                line = br.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(ArffFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
