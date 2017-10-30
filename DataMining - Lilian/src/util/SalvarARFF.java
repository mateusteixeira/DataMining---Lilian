/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import interfaces.Base;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TyTu
 */
public class SalvarARFF {

    public static boolean salvarBase(Base b, String nome) {
        String valor = new String();

        valor += nome(nome);

        valor += atributos(b);

        valor += dados(b);

        return salvar(valor, nome);


    }

    private static String nome(String nome) {
        return "@relation " + nome + "\n";
    }

    private static String atributos(Base b) {
        String s = new String();
        for (int i = 0; i < b.getAtributes().length - 1; i++) {

            s += "@attribute ";
            s += b.getAtributes()[i] + " ";
            if (b.getInput()[i][0] instanceof Number) {
                s += "NUMERIC\n";
            } else {
                s += "string\n";
            }

        }

        s += "@attribute ";
        s += b.getAtributes()[b.getAtributes().length - 1] + " ";
        if (b.getOutput()[0] instanceof Number) {
            s += "NUMERIC\n";
        } else {
            s += "string\n";
        }

        return s;
    }

    private static String dados(Base b) {
        String s = new String();
        s += "@data\n";
        for (int observacao = 0; observacao < b.getInput().length; observacao++) {

            for (int atributo = 0; atributo < b.getInput()[0].length; atributo++) {
                s += b.getInput()[observacao][atributo].toString() + ",";

            }
            s += b.getOutput()[observacao].toString();

            s += "\n";
        }
        return s;
    }

    private static boolean salvar(String valor, String nome) {
        boolean salvou = false;

        File arquivo;

        arquivo = new File(nome + ".arff");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(arquivo); // Perceba que estamos instanciando uma classe aqui. A FileOutputStream. Pesquise sobre ela!

            fos.write(valor.getBytes());

            fos.close();  // Fecha o arquivo. Nunca esquecer disso. 
            
            salvou= true;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SalvarARFF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SalvarARFF.class.getName()).log(Level.SEVERE, null, ex);
        }



        return salvou;
    }
}
