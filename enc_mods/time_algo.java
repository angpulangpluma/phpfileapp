/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enc_mods;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Marienne Lopez
 */
public class time_algo {
    
//    private aes basealgo;
    private text_aes textalgo;
    private file_aes filealgo;
    private FileWriter fw;
    private BufferedWriter bw;
    
    public time_algo(){
        this.textalgo = null;
        this.filealgo = null;
        this.fw = null;
        this.bw = null;
    }
    
    public time_algo(text_aes ta){
        this.textalgo = ta;
    }
    
    public time_algo(file_aes fa){
        this.filealgo = fa;
    }
    
    public void setFileLog(FileWriter fw) throws Exception{
        this.fw = fw;
        this.bw = new BufferedWriter(this.fw);
        writeNewLine();
        //write current time of entry
        if (textalgo!=null)
            bw.write("testing text encryption algorithm");
        if (filealgo!=null)
            bw.write("testing file encryption algorithm");
        writeNewLine();
        //find out what bw.flush() does again???
        bw.flush();
    }
    
    public void writeNewLine() throws Exception{
        bw.write("-------");
        bw.newLine();
        //find out what bw.flush() does again???
        bw.flush();
    }
    
    public void writeEncTime(int n, File file, String string) throws Exception{
        writeNewLine();
        switch(n){
            case 21: //aes encryption - text
             {
                bw.write("testing aes text encryption");
                bw.write("input: " + string);
                bw.write("output: " + textalgo.getEncString(string));
                bw.write("time elapsed: " + getEncTime(n, file, string));
                writeNewLine();
                bw.flush();
             }; break;
            case 22: //hash encryption - text
             {
                bw.write("testing aes text encryption");
                bw.write("input: " + string);
                bw.write("output: " + textalgo.getHashedString(string));
                bw.write("time elapsed: " + getEncTime(n, file, string));
                writeNewLine();
                bw.flush();
             }; break;
            case 3: //aes encryption - file
             {
                bw.write("testing aes text encryption");
                //find out how to know filesize through java
                //before encrypting filesize
                //after encrypting filesize
                bw.write("time elapsed: " + getEncTime(n, file, string));
                writeNewLine();
                bw.flush();
             }; break;
        }
    }
    
    public void writeDecTime(int n, File file, String string) throws Exception{
        writeNewLine();
        switch(n){
            case 2: //aes encryption - text
             {
                bw.write("testing aes text encryption");
                bw.write("input: " + string);
                bw.write("output: " + textalgo.getDecString(string));
                bw.write("time elapsed: " + getDecTime(n, file, string));
                writeNewLine();
                bw.flush();
             }; break;
            case 3: //aes encryption - file
             {
                bw.write("testing aes text encryption");
                //find out how to know filesize through java
                //before encrypting filesize
                //after encrypting filesize
                bw.write("time elapsed: " + getDecTime(n, file, string));
                writeNewLine();
                bw.flush();
             }; break;
        }
    }
    
    public long getEncTime(int n, File file, String string){
        long time = 0;
        long starttime = 0;
        long endtime = 0;
        
        switch(n){
            case 21: //aes encryption - text
             {
                starttime = System.currentTimeMillis();
                textalgo.getEncString(string);
                endtime = System.currentTimeMillis();
             }; break;
            case 22: //hash encryption - text
             {
                starttime = System.currentTimeMillis();
                textalgo.getHashedString(string);
                endtime = System.currentTimeMillis();
             }; break;
            case 3: //aes encryption - file
             {
                starttime = System.currentTimeMillis();
                filealgo.encryptFile(file);
                endtime = System.currentTimeMillis();
             }; break;
        }
        
        return endtime - starttime;
    }
    
    public long getDecTime(int n, File file, String string){
        long time = 0;
        long starttime = 0;
        long endtime = 0;
        
        switch(n){
            case 2: 
             {
                starttime = System.currentTimeMillis();
                textalgo.getDecString(string);
                endtime = System.currentTimeMillis();
             }; break;
            case 3:
             {
                starttime = System.currentTimeMillis();
                filealgo.decryptFile(file);
                endtime = System.currentTimeMillis();
             }; break;
        }
        
        return endtime - starttime;
    }
    
    public void finishTest() throws Exception{
        if (bw!=null)
            bw.close();
        if (fw!=null)
            fw.close();
    }
    
    

}
