package br.com.ufpr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input {

    public void lerArquivo() throws IOException {
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/br/com/arquivo/read.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

}
