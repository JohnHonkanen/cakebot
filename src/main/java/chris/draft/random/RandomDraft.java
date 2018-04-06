/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chris.draft.random;
import sx.blah.discord.handle.obj.IMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author JCH
 */

public class RandomDraft {
    private final String pathToCivFile = "src/main/resources/civilizations.txt";
    private ArrayList<String> civs;
    
    public void Load()
    {
        try
        {
            File file = new File(pathToCivFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                civs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    public void Draft(boolean isAdmin, List<String> arguments, IMessage message) {
        Integer amountToDraft = Integer.parseInt(arguments.get(0));
        
        amountToDraft = (amountToDraft < 0 ) ? amountToDraft = 12 : amountToDraft;
        amountToDraft = (amountToDraft > civs.size()) ? amountToDraft = 12 : amountToDraft;
        
        ArrayList<String> temp = civs;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < amountToDraft; i++)
        {
            int randomCiv = ThreadLocalRandom.current().nextInt(0, temp.size() + 1);
            stringBuilder.append(temp.get(randomCiv));
            if(i != amountToDraft-1)
            {
                stringBuilder.append(",");
            } 
        }
        
        message.reply(stringBuilder.toString());
    }
}
